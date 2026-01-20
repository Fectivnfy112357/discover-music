import React, { useEffect, useState, useRef } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { parseLrc } from '../utils/lrcParser';
import { Play, Pause, Download, ArrowLeft, Disc, Music2, X, Check, Loader2, Cloud } from 'lucide-react';

const Detail = () => {
  const { source, rid } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const songInfo = location.state?.songInfo || {};

  const [data, setData] = useState(null); 
  const [lyrics, setLyrics] = useState([]);
  const [loading, setLoading] = useState(true);
  const [playing, setPlaying] = useState(false);
  const [currentTime, setCurrentTime] = useState(0);
  
  // 下载与转存状态
  const [showDownloadModal, setShowDownloadModal] = useState(false);
  const [actionType, setActionType] = useState('local'); // 'local' or 'server'
  const [processing, setProcessing] = useState(false);
  const [downloadOpts, setDownloadOpts] = useState({ lyric: true, cover: true });

  const audioRef = useRef(null);
  const lyricContainerRef = useRef(null);

  useEffect(() => {
    let isMounted = true;
    const fetchData = async () => {
      setLoading(true);
      try {
        const res = await axios.get(`/api/music/detail/${source}`, {
          params: { id: rid, level: 'standard' }
        });
        if (isMounted && res.data.code === 200) {
            setData(res.data.data);
            setLyrics(parseLrc(res.data.data.lrc));
        }
      } catch (err) {
        console.error(err);
      } finally {
        if (isMounted) setLoading(false);
      }
    };
    fetchData();
    return () => { isMounted = false; };
  }, [source, rid]);

  const togglePlay = () => {
    if (!audioRef.current) return;
    if (playing) audioRef.current.pause();
    else audioRef.current.play();
    setPlaying(!playing);
  };

  const handleTimeUpdate = () => {
    if (!audioRef.current) return;
    const curr = audioRef.current.currentTime;
    setCurrentTime(curr);
    
    const activeIdx = lyrics.findIndex((l, i) => curr >= l.time && curr < (lyrics[i+1]?.time || Infinity));
    if (activeIdx !== -1 && lyricContainerRef.current) {
        const rowHeight = 40; 
        const containerHeight = lyricContainerRef.current.clientHeight;
        const scrollTo = activeIdx * rowHeight - containerHeight / 2 + rowHeight / 2;
        
        lyricContainerRef.current.scrollTo({
            top: scrollTo,
            behavior: 'smooth'
        });
    }
  };

  // 本地打包下载
  const executeLocalDownload = async (targetLevel, targetUrl) => {
    setProcessing(true);
    try {
        const artist = songInfo.artist || data.artist || 'Unknown';
        const name = songInfo.name || data.name || 'Unknown';
        const payload = { fileName: `${artist}-${name}.zip` };
        const audioExt = targetUrl.includes('.flac') ? 'flac' : 'mp3';
        payload.audioUrl = targetUrl;
        payload.audioName = `${artist}-${name}.${audioExt}`;

        if (downloadOpts.lyric && data.lrc) {
            payload.lyricText = data.lrc;
            payload.lyricName = `${artist}-${name}.lrc`;
        }
        if (downloadOpts.cover && (data.pic || songInfo.pic)) {
            payload.coverUrl = data.pic || songInfo.pic;
            payload.coverName = `${artist}-${name}-cover.jpg`;
        }

        const res = await axios.post('/api/music/download/batch', payload, { responseType: 'blob' });
        const blob = new Blob([res.data], { type: 'application/zip' });
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = payload.fileName;
        document.body.appendChild(link);
        link.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(link);
        setShowDownloadModal(false);
    } catch (e) {
        alert("打包下载失败");
    } finally {
        setProcessing(false);
    }
  };

  // 保存到服务器
  const executeSaveToServer = async (targetLevel, targetUrl) => {
    setProcessing(true);
    try {
        const payload = {
            audioUrl: targetUrl,
            artist: songInfo.artist || data.artist,
            album: songInfo.album || data.album,
            songName: songInfo.name || data.name
        };
        if (downloadOpts.lyric && data.lrc) payload.lyricText = data.lrc;
        if (downloadOpts.cover && (data.pic || songInfo.pic)) payload.coverUrl = data.pic || songInfo.pic;

        const res = await axios.post('/api/music/save-to-server', payload);
        
        // 兼容性处理：如果返回的是 JSON 字符串，手动解析
        let resData = res.data;
        if (typeof resData === 'string') {
            try {
                resData = JSON.parse(resData);
            } catch (e) {
                console.error("Parse JSON failed", e);
            }
        }

        if (resData.code === 200) {
            alert(`已成功转存至服务器！\n路径：${resData.path}`);
            setShowDownloadModal(false);
        } else {
            alert(`保存失败: ${resData.msg || 'Unknown error'}`);
        }
    } catch (e) {
        console.error(e);
        alert("请求服务器失败");
    } finally {
        setProcessing(false);
    }
  };

  const handleQualitySelect = async (level) => {
    setProcessing(true);
    try {
        const res = await axios.get(`/api/music/detail/${source}`, {
            params: { id: rid, level: level }
        });
        if (res.data.code === 200 && res.data.data?.url) {
            if (actionType === 'local') {
                await executeLocalDownload(level, res.data.data.url);
            } else {
                await executeSaveToServer(level, res.data.data.url);
            }
        } else {
            alert('获取链接失败');
            setProcessing(false);
        }
    } catch (e) {
        alert('请求失败');
        setProcessing(false);
    }
  };

  if (loading) return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <Loader2 className="w-10 h-10 animate-spin text-blue-600" />
    </div>
  );

  const bgImage = data.pic || songInfo.pic || '/placeholder.png';

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4 relative overflow-hidden">
        <div 
            className="fixed inset-0 z-0 opacity-30 blur-3xl scale-125 transition-all duration-1000"
            style={{ backgroundImage: `url(${bgImage})`, backgroundSize: 'cover', backgroundPosition: 'center' }}
        />

        <div className="bg-white/80 backdrop-blur-2xl w-full max-w-6xl h-[85vh] rounded-[2rem] shadow-2xl overflow-hidden relative z-10 flex flex-col md:flex-row border border-white/50 ring-1 ring-black/5">
            <div className="w-full md:w-[45%] p-6 md:p-12 flex flex-col bg-gradient-to-br from-white/60 to-white/20 z-20">
                <div className="flex justify-between items-start mb-4 md:mb-8 shrink-0">
                    <button onClick={() => navigate(-1)} className="p-3 hover:bg-black/5 rounded-full transition-colors group">
                        <ArrowLeft className="w-6 h-6 text-gray-700 group-hover:-translate-x-1 transition-transform" />
                    </button>
                    <div className="px-3 py-1 bg-black/5 rounded-full text-xs font-medium text-gray-500 uppercase tracking-wider">
                        {source.toUpperCase()} Music
                    </div>
                </div>
                <div className="flex-1 flex flex-col items-center justify-center min-h-0 overflow-hidden">
                    <div className="relative aspect-square w-full max-w-[260px] md:max-w-[320px] mx-auto shadow-2xl rounded-[2rem] overflow-hidden mb-6 md:mb-10 group ring-4 ring-white/50 shrink-0">
                        <img src={bgImage} alt="cover" className={`w-full h-full object-cover transition-transform duration-[20s] ease-linear ${playing ? 'scale-110 rotate-3' : 'scale-100'}`} />
                        <div className="absolute inset-0 bg-gradient-to-tr from-black/20 to-transparent pointer-events-none"></div>
                    </div>
                    <div className="text-center space-y-2 max-w-sm px-4">
                        <h1 className="text-xl md:text-3xl font-bold text-gray-900 leading-tight line-clamp-2">{data.name}</h1>
                        <p className="text-base md:text-lg text-blue-600 font-medium line-clamp-1">{data.artist}</p>
                        <p className="text-xs md:text-sm text-gray-500 line-clamp-1">{data.album}</p>
                    </div>
                </div>
            </div>

            <div className="w-full md:w-[55%] bg-white/40 flex flex-col relative z-20 overflow-hidden">
                <div className="flex-1 min-h-0 w-full relative group">
                    <div ref={lyricContainerRef} className="h-full w-full overflow-y-auto no-scrollbar text-center py-8 px-6 md:px-12 scroll-smooth" style={{ maskImage: 'linear-gradient(to bottom, transparent 0%, black 15%, black 85%, transparent 100%)' }}>
                        <div className="h-[40%]"></div>
                        {lyrics.length > 0 ? lyrics.map((line, i) => {
                            const active = currentTime >= line.time && currentTime < (lyrics[i+1]?.time || Infinity);
                            return (
                                <p key={i} className={`transition-all duration-500 py-3 cursor-default ${active ? 'text-gray-900 font-bold text-xl md:text-2xl scale-110 drop-shadow-sm' : 'text-gray-400 text-sm md:text-base hover:text-gray-600'}`}>
                                    {line.text}
                                </p>
                            )
                        }) : (
                            <div className="h-full flex flex-col items-center justify-center text-gray-400 opacity-60">
                                <Music2 className="w-16 h-16 mb-4 stroke-1" />
                                <p className="text-lg font-light">纯音乐，请欣赏</p>
                            </div>
                        )}
                        <div className="h-[40%]"></div>
                    </div>
                </div>

                <div className="shrink-0 z-30 px-6 md:px-12 pb-6 md:pb-12 pt-4">
                    <div className="w-full bg-gray-200/50 rounded-full h-1.5 mb-6 overflow-hidden cursor-pointer group/progress">
                        <div className="bg-blue-600 h-full rounded-full transition-all duration-100 group-hover/progress:bg-blue-500 relative" style={{ width: `${(currentTime / (audioRef.current?.duration || 100)) * 100}%` }}></div>
                    </div>
                    <div className="flex items-center justify-between">
                         <div className="flex items-center space-x-4 md:space-x-6">
                            <button onClick={togglePlay} className="w-12 h-12 md:w-16 md:h-16 bg-blue-600 hover:bg-blue-700 text-white rounded-full flex items-center justify-center shadow-blue-200 shadow-xl transition-all hover:scale-105 active:scale-95 cursor-pointer">
                                {playing ? <Pause className="w-6 h-6 md:w-7 md:h-7 fill-current" /> : <Play className="w-6 h-6 md:w-7 md:h-7 fill-current ml-1" />}
                            </button>
                            <div className="text-xs md:text-sm font-medium text-gray-500 tabular-nums">
                                {formatTime(currentTime)} <span className="text-gray-300 mx-1">/</span> {data.duration}
                            </div>
                         </div>
                         <div className="flex items-center space-x-2">
                            <button onClick={() => { setActionType('local'); setShowDownloadModal(true); }} className="flex items-center space-x-2 px-4 py-2 md:px-5 md:py-3 bg-white hover:bg-gray-50 text-gray-800 rounded-xl font-medium shadow-sm border border-gray-100 transition-all hover:-translate-y-0.5">
                                <Download className="w-4 h-4 md:w-5 md:h-5" />
                                <span className="hidden sm:inline">下载</span>
                            </button>
                            <button onClick={() => { setActionType('server'); setShowDownloadModal(true); }} className="flex items-center space-x-2 px-4 py-2 md:px-5 md:py-3 bg-blue-50 hover:bg-blue-100 text-blue-700 rounded-xl font-medium shadow-sm border border-blue-100 transition-all hover:-translate-y-0.5">
                                <Cloud className="w-4 h-4 md:w-5 md:h-5" />
                                <span className="hidden sm:inline">转存</span>
                            </button>
                         </div>
                    </div>
                    <audio ref={audioRef} src={data.url} onTimeUpdate={handleTimeUpdate} onEnded={() => setPlaying(false)} className="hidden" />
                </div>
            </div>
        </div>

        {/* Modal */} 
        {showDownloadModal && (
            <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/20 backdrop-blur-sm">
                <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md p-6">
                    <div className="flex justify-between items-center mb-6">
                        <h3 className="text-xl font-bold text-gray-900">{actionType === 'local' ? '打包下载到本地' : '转存到 Linux 服务器'}</h3>
                        <button onClick={() => setShowDownloadModal(false)} className="p-1 hover:bg-gray-100 rounded-full text-gray-500"><X className="w-5 h-5" /></button>
                    </div>
                    <div className="flex space-x-6 mb-6 px-1">
                        <label className="flex items-center space-x-2 cursor-pointer group">
                            <div className={`w-5 h-5 rounded border flex items-center justify-center transition-colors ${downloadOpts.lyric ? 'bg-blue-600 border-blue-600' : 'border-gray-300 group-hover:border-blue-400'}`}>{downloadOpts.lyric && <Check className="w-3.5 h-3.5 text-white" />}</div>
                            <input type="checkbox" className="hidden" checked={downloadOpts.lyric} onChange={e => setDownloadOpts(prev => ({...prev, lyric: e.target.checked}))} />
                            <span className="text-gray-700">含歌词</span>
                        </label>
                        <label className="flex items-center space-x-2 cursor-pointer group">
                            <div className={`w-5 h-5 rounded border flex items-center justify-center transition-colors ${downloadOpts.cover ? 'bg-blue-600 border-blue-600' : 'border-gray-300 group-hover:border-blue-400'}`}>{downloadOpts.cover && <Check className="w-3.5 h-3.5 text-white" />}</div>
                            <input type="checkbox" className="hidden" checked={downloadOpts.cover} onChange={e => setDownloadOpts(prev => ({...prev, cover: e.target.checked}))} />
                            <span className="text-gray-700">含封面</span>
                        </label>
                    </div>
                    <div className="space-y-2">
                        {processing ? (
                            <div className="py-8 flex flex-col items-center justify-center text-blue-600">
                                <Loader2 className="w-8 h-8 animate-spin mb-2" /><p className="text-sm font-medium">{actionType === 'local' ? '打包下载中...' : '服务器转存中...'}</p>
                            </div>
                        ) : (
                            songInfo.quality?.map((q, idx) => (
                                <button key={idx} onClick={() => handleQualitySelect(q.level)} className="w-full flex items-center justify-between p-4 rounded-xl border border-gray-100 hover:border-blue-500 hover:bg-blue-50 transition-all group">
                                    <div className="text-left">
                                        <div className="font-semibold text-gray-900 group-hover:text-blue-700">{q.quality}</div>
                                        <div className="text-xs text-gray-500 uppercase mt-0.5">{q.level}</div>
                                    </div>
                                    <div className="text-sm font-medium text-gray-400 group-hover:text-blue-600">{q.size}</div>
                                </button>
                            )) || <button onClick={() => handleQualitySelect('standard')} className="w-full bg-blue-600 text-white py-3 rounded-xl hover:bg-blue-700 font-medium">默认音质</button>
                        )}
                    </div>
                </div>
            </div>
        )}
    </div>
  );
};

const formatTime = (seconds) => {
    if (!seconds) return "00:00";
    const min = Math.floor(seconds / 60);
    const sec = Math.floor(seconds % 60);
    return `${min.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}`;
};

export default Detail;
