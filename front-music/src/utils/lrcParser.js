export const parseLrc = (lrcString) => {
    if (!lrcString) return [];
    // 统一处理换行符，兼容 Windows/Linux 格式
    const lines = lrcString.split(/\r?\n/);
    // 正则说明：匹配 [mm:ss.xx] 或 [mm:ss.xxx]，毫秒分隔符兼容 . 和 :
    const regex = /^\[(\d{1,2}):(\d{1,2})[.:](\d{1,3})\](.*)$/;
    const result = [];
  
    lines.forEach(line => {
      const trimmedLine = line.trim();
      const match = trimmedLine.match(regex);
      if (match) {
        const min = parseInt(match[1]);
        const sec = parseInt(match[2]);
        // 处理毫秒：如果是2位(如.10)则是100ms，如果是3位则是实际数值
        let msStr = match[3];
        const ms = parseInt(msStr.length === 2 ? msStr + "0" : msStr);
        
        const text = match[4].trim();
        // 即使文本为空也保留，作为占位符保持节奏，或者过滤掉完全空的行
        if (text || result.length > 0) { 
            result.push({
              time: min * 60 + sec + ms / 1000,
              text: text
            });
        }
      }
    });
    return result;
  };