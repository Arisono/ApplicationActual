package com.app.demo.util;

import java.util.regex.Pattern;

import android.os.Environment;

public interface Constants {
	
	/** @注释：加载成功 */
	public static final int LOAD_SUCESS = 1;
	/** @注释：网络未连接 */
	public static final int LOAD_NOTNETWORK = 0;
	/** @注释： 服务器异常 */
	public static final int LOAD_EXCEPTION = 2;
	
	//1,notes----------------------------------------------------------------------------
	public static final String[] KEYBOARD_SHORTCUTS = {"*", "-", "_", "#", "!", ":", ">"};
    public static final String[] KEYBOARD_SHORTCUTS_BRACKETS = { "(", ")", "[", "]"};
    public static final String[] KEYBOARD_SMART_SHORTCUTS = {"()", "[]"};
    public static final String UTF_CHARSET = "utf-8";

    public static final String MD_EXT = ".md";

    public static final int MAX_TITLE_LENGTH = 20;
    public static final String SET_PIN_ACTION = "set_pin";
    public static final String CREATE_FOLDER_DIALOG_TAG = "create_folder_dialog_tag";
    public static final String CURRENT_FOLDER_CHANGED = "current_folder_changed";
    public static final String FOLDER_NAME = "folder_name";
    public static final String FILESYSTEM_FILE_NAME = "filesystem_file_name";

    // ----- DIALOG TAGS -----
    public static final String SHARE_BROADCAST_TAG = "share_broadcast_tag";
    public static final String FILESYSTEM_IMPORT_DIALOG_TAG = "filesystem_import_dialog_tag";
    public static final String FILESYSTEM_MOVE_DIALOG_TAG = "filesystem_move_dialog_tag";
    public static final String FILESYSTEM_SELECT_FOLDER_TAG = "filesystem_select_folder_dialog_tag";
    public static final String CONFIRM_DELETE_DIALOG_TAG = "confirm_delete_dialog_tag";
    public static final String CONFIRM_OVERWRITE_DIALOG_TAG = "confirm_overwrite_dialog_tag";
    public static final String RENAME_DIALOG_TAG = "RENAME_DIALOG_TAG";

    // ----- KEYS -----
    public static final String CURRENT_DIRECTORY_DIALOG_KEY = "current_dir_folder_key";
    public static final String FILESYSTEM_ACTIVITY_ACCESS_TYPE_KEY = "FILESYSTEM_ACTIVITY_ACCESS_TYPE_KEY";
    public static final String FILESYSTEM_FOLDER_ACCESS_TYPE = "FILESYSTEM_FOLDER_ACCESS_TYPE";
    public static final String FILESYSTEM_SELECT_FOLDER_ACCESS_TYPE = "FILESYSTEM_SELECT_FOLDER_ACCESS_TYPE";
    public static final String FILESYSTEM_SELECT_FOLDER_FOR_WIDGET_ACCESS_TYPE = "FILESYSTEM_SELECT_FOLDER_FOR_WIDGET_ACCESS_TYPE";
    public static final String FILESYSTEM_FILE_ACCESS_TYPE = "FILESYSTEM_FILE_ACCESS_TYPE";
    public static final String NOTE_KEY = "note_key";
    public static final String MD_PREVIEW_BASE = "md_preview_base";
    public static final String MD_PREVIEW_KEY = "md_preview_key";
    public static final String USER_PIN_KEY = "user_pin";
    public static final String RENAME_NEW_NAME = "RENAME_NEW_NAME";
    public static final String SOURCE_FILE = "SOURCE_FILE";
    public static final String CURRENT_FOLDER = "filesystem_current_folder";
    public static final String ROOT_DIR = "filesystem_root_dir";

    // ----- DEFAULT FOLDERS -----
    public static final String WRITEILY_FOLDER = "/writeily/";

    // ----- REQUEST CODES -----
    public static final int SET_PIN_REQUEST_CODE = 3;

    // ----- HTML PREFIX AND SUFFIXES -----
    public static final String UNSTYLED_HTML_PREFIX = "<html><body>";
    public static final String MD_HTML_PREFIX = "<html><head><style type=\"text/css\">html,body{padding:4px 8px 4px 8px;font-family:'sans-serif-light';color:#303030;}h1,h2,h3,h4,h5,h6{font-family:'sans-serif-condensed';}a{color:#388E3C;text-decoration:underline;}img{height:auto;width:325px;margin:auto;}</style></head><body>";
    public static final String DARK_MD_HTML_PREFIX = "<html><head><style type=\"text/css\">html,body{padding:4px 8px 4px 8px;font-family:'sans-serif-light';color:#ffffff;background-color:#303030;}h1,h2,h3,h4,h5,h6{font-family:'sans-serif-condensed';}a{color:#388E3C;text-decoration:underline;}a:visited{color:#dddddd;}img{height:auto;width:325px;margin:auto;}</style></head><body>";
    public static final String MD_HTML_SUFFIX = "</body></html>";
    public static final String TARGET_DIR = "note_source_dir";

    // ----- INTENT EXTRAS -----
    public static final String INTENT_EXTRA_SHOW_ABOUT = "writeily.intent.settings.ABOUT";

    public static final String DEFAULT_WRITEILY_STORAGE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.WRITEILY_FOLDER;

    public static Pattern MD_EXTENSION = Pattern.compile("((?i)\\.md$)");

    public static final String WIDGET_PATH = "WIDGET_PATH";
    
    
    //2,music-----------------------------------------------------------------------------------------
    //歌手和专辑列表点击都会进入MyMusic 此时要传递参数表明是从哪里进入的
  	public static final String FROM = "from";
  	public static final int START_FROM_ARTIST = 1;
  	public static final int START_FROM_ALBUM = 2;
  	public static final int START_FROM_LOCAL = 3;
  	public static final int START_FROM_FOLDER = 4;
  	public static final int START_FROM_FAVORITE = 5;
  	//**preference
  	public static final String SP_NAME = "com.ldw.music_preference";
  	public static final String SP_BG_PATH = "bg_path";
	public static final String SP_SHAKE_CHANGE_SONG = "shake_change_song";
	public static final String SP_AUTO_DOWNLOAD_LYRIC = "auto_download_lyric";
	public static final String SP_FILTER_SIZE = "filter_size";
	public static final String SP_FILTER_TIME = "filter_time";
	
	public static final String BROADCAST_CHANGEBG = "com.ldw.music.changebg";
	public static final String SERVICE_NAME = "com.ldw.music.service.MediaService";
	public static final String BROADCAST_NAME = "com.ldw.music.broadcast";
	public static final String BROADCAST_QUERY_COMPLETE_NAME = "com.ldw.music.querycomplete.broadcast";
}
