# UIKit - ScreenAdapt

`ScreenAdapt`是一个极简的屏幕适配解决方案。

## 1、使用
在项目中`Application`的子类中添加如下代码：
```kotlin
ScreenAdapter.instance.adapt(this, 375F)
```
其中，`375`是想要适配的目标屏幕宽度，通常是设计稿中的屏幕宽度值即可。

**注意：** 别忘了在`AndroidManifest.xml`文件中注册`Application`！