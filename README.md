# UIKit

[![](https://jitpack.io/v/ITGungnir/UIKit.svg)](https://jitpack.io/#ITGungnir/UIKit)
![License](https://img.shields.io/badge/License-Apache2.0-blue.svg)
![](https://img.shields.io/badge/Email-itgungnir@163.com-ff69b4.svg)

`UIKit`是一个控件库，其中提供的控件及文档如下：
* [Banner](./docs/Banner.md)：一个可以自动无限循环滚动的控件，可自定义设置自动滚动和无限滚动
* [RichText](./docs/RichText.md)：用于方便快捷的展示富文本的控件
* [StatusView](./docs/StatusView.md)：一个可以在不同状态下切换的控件

## 使用
在项目根目录下的`build.gradle`文件中添加`JitPack`仓库的引用：
```groovy
buildscript {
    // ... Your codes
    repositories {
        // ... Your codes
        maven { url 'https://jitpack.io' }
    }
}
```
在子模块的`build.gradle`文件中加入如下依赖：
```groovy
dependencies {
    // ... Your codes
    api "com.github.ITGungnir:UIKit:$uikit_version"
}
```
参考不同控件的相关文档，使用控件即可。

## License
```text
Copyright 2019 ITGungnir

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```