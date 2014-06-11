package com.framework;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.ContextLoader;

/**
 * Created by LIUYONG on 14-2-10.
 */
public interface Constant {

    /**
     * url根路径bathPath,从shiro的Session中取得
     */
    String bathPath = (String)SecurityUtils.getSubject().getSession().getAttribute("bathPath");

    /**
     * ajax校验可用字符串返回值
     */
    String available = "<img src='" + bathPath + "resources/style/images/yes-1.png' style='height:15px;'/>可用";

    /**
     * ajax校验不可用字符串返回值
     */
    String invalid = "<img src='" + bathPath + "resources/style/images/no-1.png' style='height:15px;'/><span style='color:red;'>已被注册</span>";

    /**
     * 绝对磁盘WEB根路径 + web页面目录
     */
    String contextPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "/";

    /**
     * 上传存放路径
     */
    String uploadPath = "resources/upload/";

    /**
     * 校验码临时路径
     */
    String validateCodeImagePath = "resources/style/images/codetemp/";

    /**
     * Resources路径
     */
    String resourcesPath = "resources/";

    /**
     * 视频转换工具路径
     */
    String ffmpegPath = resourcesPath + "tools/ffmpeg/bin/ffmpeg.exe";

    /**
     * 视频转换工具ffmpeg解码支持格式
     */
    String[] decodeSupport = {"4xm", "aac", "ac3", "act", "adf", "adp", "adx", "aea", "afc", "aiff",
          "alaw", "amr", "anm", "apc", "ape", "aqtitle", "asf", "ass", "ast", "au",
          "avi", "avisynth", "avr", "avs", "bethsoftvid", "bfi", "bin", "bink", "bit",
          "bmv", "boa", "brstm", "c93", "caf", "cavsvideo", "cdg", "cdxl", "concat",
          "data", "daud", "dfa", "dirac", "dnxhd", "dshow", "dsicin", "dts", "dtshd",
          "dv", "dxa", "ea", "ea_cdata", "eac3", "epaf", "f32be", "f32le", "f64be",
          "f64le", "ffm", "ffmetadata", "film_cpk", "filmstrip", "flac", "flic", "flv",
          "frm", "g722", "g723_1", "g729", "gif", "gsm", "gxf", "h261", "h263", "h264",
          "hevc", "hls", "hnm", "ico", "idcin", "idf", "iff", "ilbc", "image2", "image2pipe",
          "ingenient", "ipmovie", "ircam", "iss", "iv8", "ivf", "jacosub", "jv", "latm",
          "lavfi", "libmodplug", "lmlm4", "loas", "lvf", "lxf", "m4v", "matroska", "webm",
          "mgsts", "microdvd", "mjpeg", "mlp", "mm", "mmf", "mov", "mp4", "mj2", "mp3",
          "mpc", "mpc8", "mpeg", "mpegts", "mpegtsraw", "mpegvideo", "mp12", "mpsub",
          "msnwctcp", "mtv", "mulaw", "mv", "mvi", "mxf", "mxg", "nc", "nistsphere", "nsv",
          "nut", "nuv", "ogg", "oma", "paf", "pjs", "pmp", "psxstr", "pva", "pvf", "qcp",
          "r3d", "rawvideo", "realtext", "redspark", "r12", "rm", "roq", "rpl", "rsd",
          "rso", "rtp", "rtsp", "s16be", "s16le", "s24be", "s24le", "s32be", "s32le",
          "s8", "sami", "sap", "sbg", "sdp", "shn", "siff", "smjpeg", "smk", "smush",
          "sol", "sox", "spdif", "srt", "subviewer", "subviewer1", "swf", "tak", "tedcaptions",
          "thp", "tiertexseq", "tmv", "truehd", "tta", "tty", "txd", "u16be", "u16le",
          "u24be", "u24le", "u32be", "u32le", "u8", "vc1", "vc1test", "vfwcap", "vivo",
          "vmd", "vobsub", "voc", "vplayer", "vqf", "w64", "wav", "wc3movie", "webvtt",
          "wsaud", "wsvqa", "wtv", "wv", "xa", "xbin", "xmv", "xwma", "yop", "yuv4mpegpipe"
    };

    /**
     * 视频转换工具ffmpeg编码支持格式
     */
    String[] encodeSupport = {"3g2", "3gp", "a64", "ac3", "adts", "adx", "aiff",
            "alaw", "amr", "asf", "asf_stream", "ass", "ast", "au",
            "avi", "avm2", "bit",
            "caca", "caf", "cavsvideo", "crc",
            "data", "daud", "dirac", "dnxhd", "dts",
            "dv", "dvd", "eac3", "f32be", "f32le", "f4v", "f64be",
            "f64le", "ffm", "ffmetadata", "filmstrip", "flac", "flv", "framecrc", "framemd5",
            "g722", "g723_1", "gif", "gxf", "h261", "h263", "h264", "hds",
            "hls", "ico", "ilbc", "image2", "image2pipe",
            "ipod", "ircam", "ismv", "ivf", "jacosub", "latm",
            "m4v", "matroska", "md5", "matroska", "webm",
            "microdvd", "mjpeg", "mkvtimestamp_v2", "mlp", "mmf", "mp2", "mp4", "mp3",
            "mpeg", "mpeg1video", "mpeg2video", "mpegts", "mpjpeg",
            "mulaw", "mxf", "mxf_d10", "null",
            "nut", "ogg", "oma", "opus", "psp",
            "rawvideo", "rm", "roq",
            "rso", "rtp", "rtsp", "s16be", "s16le", "s24be", "s24le", "s32be", "s32le",
            "s8", "sap", "sdl", "segment", "smjpeg", "smoothstreaming",
            "sox", "spdif", "speex", "srt", "stream_segment", "ssegment", "swf", "svcd", "tee",
            "truehd", "u16be", "u16le",
            "u24be", "u24le", "u32be", "u32le", "u8", "vc1", "vc1test", "vcd",
            "vob", "voc", "w64", "wav", "wc3movie", "webm", "webvtt",
            "wtv", "wv", "yuv4mpegpipe"
    };
}
