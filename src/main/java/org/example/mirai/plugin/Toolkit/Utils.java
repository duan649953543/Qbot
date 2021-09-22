package org.example.mirai.plugin.toolkit;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import okhttp3.*;
import org.example.mirai.plugin.JavaPluginMain;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Utils class
 *
 * @author 649953543@qq.com
 * @date 2021/09/22
 */

public class Utils {

    /**
     * okhttp get请求
     */
    public String okHttpClientGet(String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36")
                .get()
                .build();
        Call call = httpClient.newCall(getRequest);
        try {
            Response response = call.execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取插件配置文件目录
     */
    public Path getPluginsPath() {
        JvmPlugin jvmPlugin = new JavaPluginMain();
        return PluginManager.INSTANCE.getPluginsConfigPath().resolve(jvmPlugin.getDescription().getName());
    }

    /**
     * 获取插件数据文件目录
     */
    public Path getPluginsDataPath() {
        JvmPlugin jvmPlugin = new JavaPluginMain();
        return PluginManager.INSTANCE.getPluginsDataPath().resolve(jvmPlugin.getDescription().getName());
    }

    /**
     * 获取星期
     */
    public String getWeek() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E", Locale.ENGLISH);
        return formatter.format(date);
    }

    /**
     * 获取日期
     */
    public String getTime() {
        // 格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        // a为am/pm的标记
        sdf.applyPattern("MMddHHmmss");
        // 获取当前时间
        Date date = new Date();
        return sdf.format(date);

    }

    public String formatText(String text) {
        text = text.replace("<table class=\"tb\"> \n", "");
        text = text.replace(" <tbody>\n", "");
        text = text.replace("  <tr> \n", "");
        text = text.replace("   <td class=\"tb_td1\">", "");
        text = text.replace("</td> \n   <td>", ":");
        text = text.replace("</td> ", "");
        text = text.replace("  </tr> \n", "");
        text = text.replace("\n   <td colspan=\"3\">", "");
        text = text.replace("精评", "");
        text = text.replace("\n   <td colspan=\"3\" style=\"line-height: 25px;\">", ":");
        text = text.replace("今日重要天象", "今日重要天象:");
        text = text.replace(" </tbody>\n", "");
        text = text.replace("</table>", "");
        text = text.replace("：", ":");
        return text;
    }

    public String getHoroscopeText(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements html = doc.select("table");
            String html1 = String.valueOf(html);
            return formatText(html1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取范围随机数
     */
    public int getRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 获取文件行数
     */
    public int getFileLen(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                int linenumber = 0;
                while (lnr.readLine() != null) {
                    linenumber++;
                }
                lnr.close();
                return linenumber;
            }
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取音乐信息
     */
    public JSONObject getMusicInfo(String musicName) {
        String url = "http://music.163.com/api/search/get/web?s=" + musicName + "&type=1&limit=1";
        String html = okHttpClientGet(url);
        JSONObject json = JSONObject.parseObject(html);
        Object musicDataJson = json.getJSONObject("result").getJSONArray("songs").get(0);
        JSONObject musicInfoJson = (JSONObject) musicDataJson;
        JSONObject artists = (JSONObject) musicInfoJson.getJSONArray("artists").get(0);

        String songName = musicInfoJson.getString("name");
        String songerName = artists.getString("name");
        String coverUrl = musicInfoJson.getJSONObject("album").getJSONObject("artist").getString("img1v1Url");
        int songId = musicInfoJson.getInteger("id");

        JSONObject musicInfo = new JSONObject();
        musicInfo.put("song_name", songName);
        musicInfo.put("songer_name", songerName);
        musicInfo.put("cover_url", coverUrl);
        musicInfo.put("song_id", songId);
        return musicInfo;
    }

    /**
     * 获取二维码路径
     */
    public String getCode(String url) {
        String codePath = getPluginsDataPath() + "/code.png";
        // 生成二维码
        QrCodeUtil.encodeQrCode(url, codePath);
        return codePath;
    }


    /**
     * 生成语音文件并返回目录
     */
    public String makeVoice(String txt) {
        Setting setting = new Setting();

        String appId = setting.getAppId();
        String apiKey = setting.getApiKey();
        String secretKey = setting.getSecretKey();

        if ("".equals(appId)) {
            return "1";
        }
        Path newsFilePath = getPluginsDataPath();
        int randomNum = getRandomNum(0, 10000);
        String filename = getTime() + randomNum + ".mp3";
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        TtsResponse res = client.synthesis(txt, "zh", 1, null);
        byte[] data = res.getData();
        org.json.JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                String filepath = newsFilePath + "/cache/voice/" + filename;
                Util.writeBytesToFileSystem(data, filepath);
                return filepath;
            } catch (IOException e) {
                e.printStackTrace();
                return "0";
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
            return "0";
        }
        return "0";
    }

    /**
     * 获取当前小时分钟
     */
    public String getNowTime() {
        // 格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        // a为am/pm的标记
        sdf.applyPattern("HH:mm");
        // 获取当前时间
        Date date = new Date();
        // 输出已经格式化的现在时间（24小时制）
        return sdf.format(date);
    }


    /**
     * 获取时间
     */
    public String getTime1() {
        // 格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        // a为am/pm的标记
        sdf.applyPattern("MMdd");
        // 获取当前时间
        Date date = new Date();
        return sdf.format(date);

    }

    /**
     * 重写星期文件
     */
    public void rewrite(int week) {
        try {
            File weekcacheFile = new File(getPluginsDataPath() + "/cache/week.cache");
            BufferedWriter out = new BufferedWriter(new FileWriter(weekcacheFile));
            out.write(String.valueOf(week));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写日期文件
     */
    public void rewriteData(File file, String qian) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", getTime1());
            jsonObject.put("qian", qian);
            out.write(String.valueOf(jsonObject));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取已抽签文件
     */
    public String readData(File file) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(file));
            String data = in.readLine();
            JSONObject jsonObject = JSONObject.parseObject(data);
            String time = jsonObject.getString("date");
            String qian = jsonObject.getString("qian");
            if (time.equals(getTime1())) {
                return qian;
            } else {
                return "1";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public boolean getNews() {
        String url = "http://api.03c3.cn/zb";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(getRequest);
        try {
            Response response = call.execute();
            ResponseBody body = response.body();
            //获取流
            assert body != null;
            InputStream in = body.byteStream();
            //转化为bitmap
            FileOutputStream fo = new FileOutputStream(getPluginsDataPath() + "/cache/" + getTime1() + ".jpg");
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close();
            fo.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

