# PictureSelectorxiugai
为了项目需求，在PictureSelector的基础上做了一些小修改

感谢LuckSiege大佬

项目地址  [LuckSiege/PictureSelector](https://github.com/LuckSiege/PictureSelector)

因项目需求，取消了不能同时选择视频和图片的限制

修改了视频预览，使播放视频时相对友好一些（我自己感觉应该是友好了）

预览视频由原先的方法(当然原先方法并没有删掉)

 ```java
 PictureSelector.create(MainActivity.this).externalPictureVideo(video_path);
 
 ```
 改为了
 ```java 
 PictureSelector.create(activity).themeStyle(R.style.picture_default_style)
                .loadImageEngine(PictureSelectorGlideEngine.createGlideEngine())
                .openExternalPictureVideo(bean.path, bean.compressPath)
 ```
 
 第一个参数没变，还是视频地址，第二个参数是缩略图地址，也就是视频其中一帧的图片
