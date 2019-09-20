# ReactDemo1
React Native调用android的功能
# Android端：
### 第一步创建android被调用的对象：
创建一个类继承ReactContextBaseJavaModule


```
public class BaseJSBridgeAndroid extends ReactContextBaseJavaModule {
    public BaseJSBridgeAndroid(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        //1.作用：用于Js端调用Android端的桥梁（你可以理解为一个全局变量）
        //2.命名：避免使用时混淆，最好是以类名同名。
        //3.使用：在js端通过 NativeModules.BaseJSBridgeAndroid.xxxandroid方法xx();
        return "BaseJSBridgeAndroid";
    }

    @ReactMethod//此方法为Android端被js端调用的方法。
    public void testAndroidToast(String msg){
        Toast.makeText(getReactApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
```
> 1.上面创建的testAndroidToast(String msg) 方法用来接收js端的调用</br>
> 2.方法的书写规则： 必须满足以下三点；
> >- 方法的权限必须是public
> >- 方法的返回类型必须是void
> >- 方法必须用@ReactMethod标记

### 第二步创建***NativeModule***
```
public class BaseReactPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new BaseJSBridgeAndroid(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
```
>这里将android的方法类注入给react使用。 
这一步只需要将第一步中创建的BaseJSBridgeAndroid类添加到createNativeModules方法中即可
### 第三步将第二步中创建的Native加入到MainApplication的ReactPackage列表中
```
public class MainApplication extends Application implements ReactApplication {
	...
        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new BaseReactPackage()
            );
        }
	...
｝
```
>到这里Android端所需要的准备就完毕了。 下面为js端需要做的事情。

# JS端：
### 第一步添加依赖：
```
import {NativeModules} from 'react-native';
```
### 第二步使用：
```
NativeModules.BaseJSBridgeAndroid.testAndroidToast('调用android端方法');
```
>这里的BaseJSBridgeAndroid是android部分第一步的getName()方法中返回的字符串；
如果还是不明白建议去下载下项目运行一遍就会立马明白了。

[github项目地址](https://github.com/LuffyHope/ReactDemo1)

### 项目下载后如何运行起来
>1.用visual studio code打开工程依次执行以下命令
>>- npm install  //下载依赖包；不下载的话运行不起来。
>>- yarn
>>- yarn start
>
>2.用android studio将工程运行到手机上</br>
>3.reload App,如果还是加载不出来那么把app进程杀死重启，然后再重复操作1.步骤。</br>
>4.如果还是不行那么执行 react-native run-android命令。
