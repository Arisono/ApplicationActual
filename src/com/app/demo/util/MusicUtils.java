package com.app.demo.util;

import java.util.HashMap;








import com.app.demo.dao.AlbumInfoDao;
import com.app.demo.dao.ArtistInfoDao;
import com.app.demo.dao.FavoriteInfoDao;
import com.app.demo.dao.FolderInfoDao;
import com.app.demo.dao.MusicInfoDao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Files.FileColumns;

public class MusicUtils implements Constants {
	
	public static final int FILTER_SIZE = 1 * 1024 * 1024;// 1MB
	public static final int FILTER_DURATION = 1 * 60 * 1000;// 1分钟
	private static final Uri sArtworkUri = Uri
			.parse("content://media/external/audio/albumart");
	//关于图像
	private static final BitmapFactory.Options sBitmapOptionsCache = new BitmapFactory.Options();
	private static final BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
	private static final HashMap<Long, Bitmap> sArtCache = new HashMap<Long, Bitmap>();
	static {
		// for the cache,
		// 565 is faster to decode and display
		// and we don't want to dither here because the image will be scaled
		// down later
		sBitmapOptionsCache.inPreferredConfig = Bitmap.Config.RGB_565;
		sBitmapOptionsCache.inDither = false;

		sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
		sBitmapOptions.inDither = false;
	}
	
	private static String[] proj_music = new String[] {
		MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
		MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID,
		MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ARTIST_ID,
		MediaStore.Audio.Media.DURATION };
	private static String[] proj_album = new String[] { Albums.ALBUM,
		Albums.NUMBER_OF_SONGS, Albums._ID, Albums.ALBUM_ART };

	private static String[] proj_artist = new String[] {
		MediaStore.Audio.Artists.ARTIST,
		MediaStore.Audio.Artists.NUMBER_OF_TRACKS };

	private static String[] proj_folder = new String[] { FileColumns.DATA };
	
	// 歌曲信息数据库
	private static MusicInfoDao mMusicInfoDao;
	// 专辑信息数据库
	private static AlbumInfoDao mAlbumInfoDao;
	// 歌手信息数据库
	private static ArtistInfoDao mArtistInfoDao;
	// 文件夹信息数据库
	private static FolderInfoDao mFolderInfoDao;
	//我的收藏信息数据库
	private static FavoriteInfoDao mFavoriteDao;
	
}
