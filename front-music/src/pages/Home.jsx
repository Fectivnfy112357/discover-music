import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Search, ChevronLeft, ChevronRight, Loader2, Music, Music2, Sparkles, Flame, Mic2, Disc, Headphones } from 'lucide-react';

const SOURCES = [
  { id: 'kw', name: '小黄源', color: 'from-yellow-400 to-orange-500', shadow: 'shadow-yellow-200' },
  { id: 'wy', name: '小红源', color: 'from-red-500 to-pink-600', shadow: 'shadow-red-200' },
  { id: 'kg', name: '小狗源', color: 'from-blue-400 to-cyan-500', shadow: 'shadow-blue-200' },
  { id: 'tx', name: '小绿源', color: 'from-green-400 to-emerald-600', shadow: 'shadow-green-200' },
  { id: 'mg', name: '小粉源', color: 'from-pink-400 to-rose-500', shadow: 'shadow-pink-200' },
];

const HOT_TAGS = ['周杰伦', '林俊杰', '陈奕迅', 'Taylor Swift', '抖音热歌', '古典音乐', 'K-Pop'];

const Home = ({ searchState, setSearchState }) => {
  const { keyword, source, results, loading, page, hasSearched } = searchState;
  const navigate = useNavigate();

  const updateState = (updates) => setSearchState(prev => ({ ...prev, ...updates }));

  const setKeyword = (val) => updateState({ keyword: val });
  const setSource = (val) => updateState({ source: val });
  const setResults = (val) => updateState({ results: val });
  const setLoading = (val) => updateState({ loading: val });
  const setPage = (val) => updateState({ page: val });
  const setHasSearched = (val) => updateState({ hasSearched: val });

  const handleSearch = async (newPage = 1, targetSource = source, targetKeyword = keyword) => {
    const searchKeyword = targetKeyword.trim();
    if (!searchKeyword) return;

    // 更新 input 显示（如果是点击标签触发的）
    if (targetKeyword !== keyword) setKeyword(targetKeyword);

    setLoading(true);
    setHasSearched(true);
    try {
      const res = await axios.get(`/api/music/search/${targetSource}`, {
        params: { keyword: searchKeyword, page: newPage }
      });
      if (res.data.code === 200) {
        setResults(res.data.data);
        setPage(newPage);
      } else {
        setResults([]);
      }
    } catch (err) {
      console.error(err);
      setResults([]);
    } finally {
      setLoading(false);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') handleSearch(1);
  };

  const onSourceChange = (newId) => {
    setSource(newId);
    if (keyword.trim()) {
        handleSearch(1, newId);
    }
  };

  // 获取当前源的样式配置
  const currentSourceStyle = SOURCES.find(s => s.id === source) || SOURCES[0];

  return (
    <div className="min-h-screen bg-slate-50 relative overflow-hidden font-sans selection:bg-blue-100">
      
      {/* 1. 背景装饰元素 (仅在未搜索或大屏下显示) */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute top-[-10%] left-[-10%] w-96 h-96 bg-purple-200 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob"></div>
        <div className="absolute top-[-10%] right-[-10%] w-96 h-96 bg-yellow-200 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob animation-delay-2000"></div>
        <div className="absolute bottom-[-20%] left-[20%] w-96 h-96 bg-pink-200 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob animation-delay-4000"></div>
        
        {/* 漂浮图标 */}
        {!hasSearched && (
            <>
                <Music2 className="absolute top-[15%] left-[10%] text-slate-200 w-16 h-16 rotate-12" />
                <Disc className="absolute bottom-[20%] right-[10%] text-slate-200 w-24 h-24 -rotate-12" />
                <Headphones className="absolute top-[20%] right-[20%] text-slate-200 w-12 h-12 rotate-45" />
            </>
        )}
      </div>

      <div className={`container mx-auto px-4 transition-all duration-700 ease-in-out flex flex-col ${hasSearched ? 'py-8' : 'h-screen justify-center'}`}>
        
        {/* 2. 头部区域 (搜索框 + Tab) */}
        <div className={`w-full max-w-3xl mx-auto z-10 transition-all duration-700 ${hasSearched ? 'mb-8' : 'text-center mb-0'}`}>
            
            {/* 标题 */}
            <div className={`transition-all duration-700 ${hasSearched ? 'hidden' : 'block mb-12'}`}>
                <h1 className="text-5xl md:text-7xl font-extrabold tracking-tight mb-4 text-slate-800">
                    Discover <span className={`bg-clip-text text-transparent bg-gradient-to-r ${currentSourceStyle.color}`}>Music</span>
                </h1>
                <p className="text-slate-500 text-lg md:text-xl">探索无尽的音乐世界，从这里开始</p>
            </div>

            {/* 搜索框容器 */}
            <div className={`relative group transition-all duration-300 ${hasSearched ? '' : 'transform hover:-translate-y-1'}`}>
                <div className={`absolute -inset-1 bg-gradient-to-r ${currentSourceStyle.color} rounded-full blur opacity-25 group-hover:opacity-50 transition duration-1000 group-hover:duration-200`}></div>
                <div className="relative bg-white rounded-full shadow-xl flex items-center p-2 border border-slate-100">
                    <div className="pl-4 pr-2 text-slate-400">
                        <Search className="w-6 h-6" />
                    </div>
                    <input
                        type="text"
                        className="flex-1 p-3 bg-transparent text-slate-700 placeholder-slate-400 text-lg focus:outline-none"
                        placeholder={hasSearched ? "搜索歌曲..." : "输入歌曲、歌手或专辑名称..."}
                        value={keyword}
                        onChange={(e) => setKeyword(e.target.value)}
                        onKeyDown={handleKeyDown}
                    />
                    <button 
                        onClick={() => handleSearch(1)}
                        className={`bg-gradient-to-r ${currentSourceStyle.color} text-white px-8 py-3 rounded-full font-semibold shadow-lg hover:shadow-xl transition-all duration-300 active:scale-95`}
                    >
                        搜索
                    </button>
                </div>
            </div>

            {/* Tab 切换 */}
            <div className={`flex flex-wrap justify-center gap-3 mt-8 transition-all duration-500 ${hasSearched ? 'justify-start mt-6' : ''}`}>
                {SOURCES.map((s) => {
                    const isActive = source === s.id;
                    return (
                        <button
                            key={s.id}
                            onClick={() => onSourceChange(s.id)}
                            className={`px-5 py-2 rounded-full text-sm font-medium transition-all duration-300 flex items-center space-x-1 ${
                                isActive 
                                    ? `bg-gradient-to-r ${s.color} text-white shadow-lg shadow-current ring-2 ring-offset-2 ring-transparent` 
                                    : 'bg-white text-slate-600 hover:bg-slate-50 border border-slate-200 hover:border-slate-300'
                            }`}
                        >
                            {isActive && <Sparkles className="w-3 h-3 animate-pulse" />}
                            <span>{s.name}</span>
                        </button>
                    )
                })}
            </div>

            {/* 热门标签 (仅在未搜索时显示) */}
            {!hasSearched && (
                <div className="mt-12 opacity-0 animate-fade-in-up" style={{ animationDelay: '0.2s', animationFillMode: 'forwards' }}>
                    <div className="flex items-center justify-center space-x-2 text-slate-400 mb-4 text-sm font-medium uppercase tracking-wider">
                        <Flame className="w-4 h-4 text-orange-400" />
                        <span>Trending Now</span>
                    </div>
                    <div className="flex flex-wrap justify-center gap-3">
                        {HOT_TAGS.map((tag, idx) => (
                            <button
                                key={idx}
                                onClick={() => handleSearch(1, source, tag)}
                                className="px-4 py-1.5 bg-white/60 hover:bg-white text-slate-600 rounded-lg text-sm border border-slate-200/50 hover:border-blue-300 transition-colors shadow-sm backdrop-blur-sm"
                            >
                                {tag}
                            </button>
                        ))}
                    </div>
                </div>
            )}
        </div>

        {/* 3. 结果列表区域 */}
        {loading && (
            <div className="flex flex-col items-center justify-center py-20 z-10">
                <Loader2 className={`w-12 h-12 animate-spin bg-clip-text text-transparent bg-gradient-to-r ${currentSourceStyle.color}`} />
                <p className="mt-4 text-slate-400 font-medium">正在搜索中...</p>
            </div>
        )}

        {!loading && results.length > 0 && (
            <div className="bg-white/80 backdrop-blur-xl rounded-[2rem] shadow-2xl overflow-hidden border border-white/50 z-10 animate-fade-in-up">
                <div className="grid grid-cols-12 gap-4 p-5 text-sm font-bold text-slate-400 border-b border-slate-100/50 bg-slate-50/50">
                    <div className="col-span-1 text-center">#</div>
                    <div className="col-span-5 pl-2">歌曲</div>
                    <div className="col-span-3">歌手</div>
                    <div className="col-span-3">专辑</div>
                </div>
                <div className="divide-y divide-slate-50">
                    {results.map((item, index) => (
                        <div 
                            key={index} 
                            onClick={() => navigate(`/detail/${source}/${item.rid}`, { state: { songInfo: item } })}
                            className="grid grid-cols-12 gap-4 p-4 items-center hover:bg-blue-50/50 transition-all duration-200 cursor-pointer group"
                        >
                            <div className="col-span-1 text-slate-400 text-sm font-mono text-center">{(page - 1) * 30 + index + 1}</div>
                            <div className="col-span-5 flex items-center space-x-4">
                                <div className="relative w-12 h-12 rounded-xl overflow-hidden shadow-sm flex-shrink-0 group-hover:shadow-md group-hover:scale-105 transition-all bg-slate-100 flex items-center justify-center">
                                    <img 
                                        src={item.pic || 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjOTRhM2I4IiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCI+PGNpcmNsZSBjeD0iMTIiIGN5PSIxMiIgcj0iMTAiLz48Y2lyY2xlIGN4PSIxMiIgY3k9IjEyIiByPSIyIi8+PC9zdmc+'} 
                                        alt={item.name} 
                                        className="w-full h-full object-cover"
                                        onError={(e) => {
                                            e.target.onerror = null;
                                            e.target.src = 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjOTRhM2I4IiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCI+PGNpcmNsZSBjeD0iMTIiIGN5PSIxMiIgcj0iMTAiLz48Y2lyY2xlIGN4PSIxMiIgY3k9IjEyIiByPSIyIi8+PC9zdmc+';
                                        }}
                                    />
                                    <div className="absolute inset-0 bg-black/0 group-hover:bg-black/10 transition-colors flex items-center justify-center">
                                         <Music2 className="w-5 h-5 text-white opacity-0 group-hover:opacity-100 transform scale-50 group-hover:scale-100 transition-all" />
                                    </div>
                                </div>
                                <div>
                                    <div className="font-semibold text-slate-800 text-lg line-clamp-1 group-hover:text-blue-600 transition-colors">{item.name}</div>
                                    <div className="flex items-center space-x-2 mt-1">
                                        <span className={`px-1.5 py-0.5 rounded text-[10px] bg-slate-100 text-slate-500 border border-slate-200`}>
                                            {item.quality && item.quality.length > 0 ? 'HQ' : 'SQ'}
                                        </span>
                                        <span className="text-xs text-slate-400 font-medium">{item.duration}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="col-span-3 flex items-center space-x-2 text-sm text-slate-600 group-hover:text-slate-900 transition-colors">
                                <Mic2 className="w-4 h-4 text-slate-300" />
                                <span className="line-clamp-1">{item.artist}</span>
                            </div>
                            <div className="col-span-3 flex items-center space-x-2 text-sm text-slate-500 group-hover:text-slate-800 transition-colors">
                                <Disc className="w-4 h-4 text-slate-300" />
                                <span className="line-clamp-1">{item.album}</span>
                            </div>
                        </div>
                    ))}
                </div>
                
                {/* Pagination */}
                <div className="flex justify-center items-center p-8 space-x-8 border-t border-slate-100 bg-slate-50/80">
                    <button 
                        disabled={page === 1}
                        onClick={() => handleSearch(page - 1)}
                        className="p-3 rounded-full bg-white border border-slate-200 hover:bg-blue-50 hover:border-blue-200 text-slate-500 hover:text-blue-600 disabled:opacity-40 disabled:cursor-not-allowed transition-all shadow-sm active:scale-95"
                    >
                        <ChevronLeft className="w-5 h-5" />
                    </button>
                    <span className="text-base font-medium text-slate-600 font-mono bg-white px-4 py-1 rounded-lg border border-slate-200 shadow-sm">
                        Page {page}
                    </span>
                    <button 
                        onClick={() => handleSearch(page + 1)}
                        className="p-3 rounded-full bg-white border border-slate-200 hover:bg-blue-50 hover:border-blue-200 text-slate-500 hover:text-blue-600 transition-all shadow-sm active:scale-95"
                    >
                        <ChevronRight className="w-5 h-5" />
                    </button>
                </div>
            </div>
        )}

        {!loading && hasSearched && results.length === 0 && (
            <div className="text-center py-20 z-10 animate-fade-in-up">
                <div className="bg-white p-8 rounded-full inline-block shadow-lg mb-6">
                    <Music className="w-16 h-16 text-slate-300" />
                </div>
                <h3 className="text-xl font-bold text-slate-700 mb-2">未找到相关歌曲</h3>
                <p className="text-slate-400">试试其他关键字，或切换音源搜索</p>
            </div>
        )}
      </div>
    </div>
  );
};

export default Home;
