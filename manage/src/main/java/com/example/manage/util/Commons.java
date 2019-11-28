package com.example.manage.util;

import com.example.manage.constant.WebConst;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author tangj
 * @date 2018/1/21 21:56
 */
@Component
public class Commons {


    public static String THEME = "themes/jantent";

    private static final List EMPTY = new ArrayList(0);


    /**
     * 判断分页中是否有数据
     */
    public static boolean is_empty(PageInfo paginator) {
        return paginator == null || (paginator.getList() == null) || (paginator.getList().size() == 0);
    }

    /**
     * 网站链接
     */
    public static String site_url() {
        return site_url("/page/1");
    }

    public static String site_index() {
        return "index";
    }

    /**
     * 在管理员页面退出登录返回到登录界面
     */
    public static String site_login() {
        return "admin/login";
    }

    /**
     * 返回网站链接下的全址
     *
     * @param sub 后面追加的地址
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站标题
     */
    public static String site_title() {
        return site_option("site_title");
    }

    /**
     * 网站配置项
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param defalutValue 默认值
     */
    public static String site_option(String key, String defalutValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return defalutValue;
        }
    }

    /**
     * 截取字符串
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 返回主题URL
     */
    public static String theme_url() {
        return site_url(Commons.THEME);
    }

    /**
     * 返回主题下的文件路径
     */
    public static String theme_url(String sub) {
        return site_url(Commons.THEME + sub);
    }

    /**
     * 返回gravatar头像地址
     */
    public static String gravatar(String email) {
        String avatarUrl = "https://secure.gravatar.com/avatar";
        if (StringUtils.isBlank(email)) {
            return avatarUrl;
        }
        String hash = MyUtils.MD5encode(email.trim().toLowerCase());
        return avatarUrl + "/" + hash;
    }

//    /**
//     * 返回文章链接地址
//     *
//     * @param contents
//     * @return
//     */
//    public static String permalink(ContentVo contents) {
//        return permalink(contents.getCid(), contents.getSlug());
//    }


    /**
     * 获取随机数
     */
//    public static String random(int max, String str) {
//        return UUID.random(1, max) + str;
//    }

    /**
     * 返回文章链接地址
     */
    public static String permalink(Integer cid, String slug) {
        return site_url("/article/" + (StringUtils.isNotBlank(slug) ? slug : cid.toString()));
    }

    /**
     * 格式化unix时间戳为日期
     */
    public static String fmtdate(Integer unixTime) {
        return fmtdate(unixTime, "yyyy-MM-dd");
    }

    /**
     * 格式化unix时间戳为日期
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && StringUtils.isNotBlank(patten)) {
            return DateKit.formatDateByUnixTime(unixTime, patten);
        }
        return "";
    }

    /**
     * 格式化unix时间戳为日期
     */
    public static String fmtdate(Date date) {

        if (null != date) {
            return DateKit.formatDate(date);
        }
        return "";
    }

    /**
     * 显示分类
     */
    public static String show_categories(String categories) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(categories)) {
            String[] arr = categories.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append(
                        "<a class=\"blog-color\" href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c
                                + "</a>");
            }
            return sbuf.toString();
        }
        return show_categories("默认分类");
    }

    /**
     * 显示标签
     */
    public static String show_tags(String tags) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(tags)) {
            String[] arr = tags.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/tag/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return "";
    }

    /**
     * 截取文章摘要
     *
     * @param article 文章
     * @param len     要截取文字的个数
     */
//    public static String intro(ProductArticle article, int len) {
//        String value = article.getArticleContent();
//        int pos = value.indexOf("<!--more-->");
//        if (pos != -1) {
//            String html = value.substring(0, pos);
//            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
//        } else {
//            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
//            if (text.length() > len) {
//                return text.substring(0, len);
//            }
//            return text;
//        }
//    }

    /**
     * 显示文章内容，转换markdown为html
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }

//    /**
//     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
//     *
//     * @return
//     */
//    public static String show_thumb(ProductArticle contents) {
//        if (StringUtils.isNotBlank(contents.getThumbimg())){
//            return contents.getThumbimg();
//        }
//        int cid = contents.getCid();
//        int size = cid % 25;
//        size = size == 0 ? 1 : size;
//        return "/user/img/rand/" + size + ".jpg";
//    }

//    /**
//     * 最新评论
//     *
//     * @param limit
//     * @return
//     */
//    public static List<CommentVo> recent_comments(int limit) {
//        if (null == siteService) {
//            return EMPTY;
//        }
//        return siteService.recentComments(limit);
//    }

    /**
     * 获取所有分类
     */
//  public static List<MetaDto> categries() {
//    return categries(WebConst.MAX_POSTS);
//  }

    /**
     * 获取所有标签
     *
     * @return
     */
    // public static List<MetaDto> tags() {
    //   return tags(WebConst.MAX_POSTS);
    // }

//    /**
//     * 获取评论at信息
//     *
//     * @param coid
//     * @return
//     */
//    public static String comment_at(Integer coid) {
//        CommentVo comments = siteService.getComment(coid);
//        if (null != comments) {
//            return "<a href=\"#comment-" + coid + "\">@" + comments.getAuthor() + "</a>";
//        }
//        return "";
//    }

    /**
     * An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
     * <p>
     * 这种格式的字符转换为emoji表情
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }

    /**
     * 获取文章第一张图片
     */
    public static String show_thumb(String content) {
        content = MyUtils.mdToHtml(content);
        if (content.contains("<img")) {
            String img = "";
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            Pattern p_image = compile(regEx_img, CASE_INSENSITIVE);
            Matcher m_image = p_image.matcher(content);
            if (m_image.find()) {
                img = img + "," + m_image.group();
                // //匹配src
                @SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
                Matcher m = compile("src\\s*=\\s*\'?\"?(.*?)(\'|\"|>|\\s+)").matcher(img);
                if (m.find()) {
                    return m.group(1);
                }
            }
        }
        return "";
    }

    private static final String[] ICONS = {"bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat",
            "bg-ico-code", "bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock"};

    /**
     * 显示文章图标
     */
    public static String show_icon(int cid) {
        return ICONS[cid % ICONS.length];
    }

//    public static String showCategoryUrl(MetaDto metaDto){
//        String url = "/category/"+metaDto.getName();
//        return url;
//    }

    /**
     * 获取社交的链接地址
     */
    public static Map<String, String> social() {
        final String prefix = "social_";
        Map<String, String> map = new HashMap<>();
        map.put("weibo", WebConst.initConfig.get(prefix + "weibo"));
        map.put("zhihu", WebConst.initConfig.get(prefix + "zhihu"));
        map.put("github", WebConst.initConfig.get(prefix + "github"));
        map.put("twitter", WebConst.initConfig.get(prefix + "twitter"));
        return map;
    }

//    /**
//     * 格式化枚举数据
//     */
//    public static String formatEunm(ArticleTypeEnum articleTypeEnum) {
//        if (null != articleTypeEnum) {
//            return articleTypeEnum.getName();
//        }
//        return "";
//    }

    /**
     * 格式化枚举数据
     */
//    public static String formatStatusEnum(ProductStatusEnum productStatusEnum) {
//        if (null != productStatusEnum) {
//            return productStatusEnum.getName();
//        }
//        return "";
//    }
}
