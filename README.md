# 安卓日历 NCalendar

## 特点:

 - 3种常见日历交互方式，MIUI系统日历：miui9、miui10、华为emui，miui9和钉钉日历类似，华为emui和365日历类似
 - 月周滑动切换
 - 支持设置默认视图，默认周日历或者月日历
 - 支持周状态固定，下拉刷新等
 - 支持设置一周开始的是周一还是周日
 - 可设置日期区间，默认区间从1901-01-01到2099-12-31 
 - 支持单独月日历和单独周日历默认不选中
 - 支持农历，节气、法定节假日等
 - 支持添加指示点及设置指示点位置
 - 支持各种颜色、距离、位置等属性


## 效果图 
|miui9|miui10|
|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/miui9_gif.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/miui10_gif.gif)|

|emui|周固定，下拉刷新|
|:---:|:---:|
|![](https://github.com/yannecer/NCalendar/blob/master/app/emui_gif.gif)|![](https://github.com/yannecer/NCalendar/blob/master/app/week_hold.gif)|
## 下载demo：
[下载demo](https://github.com/yannecer/NCalendar/releases/download/3.2.1/app.apk)

## 使用方法

#### Gradle
```
implementation 'com.necer.ncalendar:ncalendar:3.2.2'

```

#### 月周切换

```
    miui9 和 钉钉日历
    <com.necer.calendar.Miui9Calendar
        android:id="@+id/miui9Calendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.necer.calendar.Miui9Calendar>
    
    miui10
    <com.necer.calendar.Miui10Calendar
        android:id="@+id/miui10Calendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.v4.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        </android.support.v4.widget.NestedScrollView>  
    </com.necer.calendar.Miui10Calendar>

    华为 和 365日历
    <com.necer.calendar.EmuiCalendar
        android:id="@+id/emuiCalendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.v4.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">           
        </android.support.v4.widget.NestedScrollView>
    </com.necer.calendar.EmuiCalendar>

```

#### 单个月日历，单个周日历


```
   月日历
   <com.necer.calendar.MonthCalendar
        android:layout_width="match_parent"
        app:todaySolarTextColor="#ff00ff"
        app:selectCircleColor="#00c3aa"
        app:hollowCircleColor="#00c3aa"
        android:layout_height="300dp" />

   周日历
   <com.necer.calendar.WeekCalendar
        android:layout_width="match_parent"
        app:lunarTextColor="#00aa00"
        android:layout_height="50dp" />

```


#### 注意

- NCalendar（Miui9Calendar、Miui10Calendar、EmuiCalendar）内部只能有一个子view，需要一个实现了```NestedScrollingChild2```的子类，
如```RecyclerView```，```NestedScrollView```等，不必是直接子类，可以使用其他布局嵌套一个```NestedScrollingChild2```
- 单个的周日历和月日历可以设置默认不选中（即是点击才选中，不点击不选中），但是月周切换必须每页都选中




### 交流群

技术交流QQ群：127278900





### 主要Api


##### 1、监听
```

NCalendar（包含Miui9Calendar、Miui10Calendar和EmuiCalendar）OnCalendarChangedListener 日期、月周状态变化回调

nCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarDateChanged(NDate date) {
               //日历回调 NDate包含公历、农历、节气、节假日、闰年等信息
            }
               
            @Override
            public void onCalendarStateChanged(boolean isMonthSate) {
               //日历状态回调， 月->周 isMonthSate返回false ，反之返回true   
            }
        });
        
其他回调

OnCalendarChangedListener         //日期、月周状态变化回调（NCalendar）
OnClickDisableDateListener        //点击区间之外的日期回调，如果不设置会弹出不可用提示（NCalendar、MonthCalendar、WeekCalendar）
OnMonthSelectListener             //月日历默认选中时 日期选中回调（MonthCalendar）
OnWeekSelectListener              //周日历默认选中时 日期选中回调（WeekCalendar）
OnYearMonthChangedListener        //月日历，周日历默认不选中是翻页回调 年 月 （MonthCalendar、WeekCalendar）

```

##### 2、跳转日期
```
参数为 yyyy-MM-dd 格式的日期

ncalendar.jumpDate("2017-12-31"); 
```
##### 3、回到今天
```
ncalendar.toToday(); 
```

##### 4、日历切换，月-->周  周-->月
```
ncalendar.toWeek();
ncalendar.toMonth();
```
##### 5、上一月、下一月、上一周、下一周
```
ncalendar.toNextPager();
ncalendar.toLastPager();
```

##### 6、添加指示圆点
```
List<String> pointList = Arrays.asList("2018-10-01", "2018-11-19", "2018-11-20", "2018-05-23", "2019-01-01");
ncalendar.setPointList(list);
```
##### 7、默认视图 
```
app:defaultCalendar="week"  默认周视图
app:defaultCalendar="month"  默认月视图
```
##### 8、周状态固定
```
app:isWeekHold="true"  周视图固定，下拉刷新
```
##### 9、设置日期区间 
```
app:startDate="2018-01-01" 开始日期
app:endDate="2018-12-31" 结束日期

或

setDateInterval(startFormatDate, endFormatDate)
```
##### 10、设置初始化日期
```
setInitializeDate(formatDate) 
```


## 感谢：

项目中日期计算使用  [joda-time](https://github.com/JodaOrg/joda-time)<br/>农历和节气数据是工具类，多谢



## 支持的属性：

|Attributes|forma|describe
|---|---|---|
|solarTextColor| color|公历日期的颜色
|lunarTextColor| color|农历日期的颜色
|solarHolidayTextColor| color|公历节假日的颜色
|lunarHolidayTextColor| color|农历节假日的颜色
|solarTermTextColor| color|节气颜色
|selectCircleColor| color|选中圈的颜色
|holidayColor|color| 法定节休息日颜色
|workdayColor|color| 法定节调休工作日颜色
|bgCalendarColor|color| 日历的背景
|bgChildColor|color| 日历包含子view的背景
|pointColor| color |小圆点的颜色
|startDate| string |日期区间开始日期
|endDate| string |日期区间结束日期
|alphaColor| integer |不是本月的日期颜色的透明度0-255
|disabledAlphaColor| integer |日期区间之外的地日颜色的透明度0-255
|disabledString| string |点击日期区间之外的日期提示语
|todaySolarTextColor| color|今天不选中的颜色
|todaySolarSelectTextColor| color|今天选中的颜色
|selectCircleRadius| dimension | 选中圈的半径
|solarTextSize| dimension|公历日期字体大小
|lunarTextSize| dimension|农历日期字体大小
|lunarDistance| dimension|农历日期到公历字体的距离
|holidayTextSize| dimension|法定节假日字体的大小
|holidayDistance| dimension |法定节假日到公历的距离
|pointDistance| dimension |小圆点到公历的距离
|hollowCircleStroke| dimension |空心圆的宽度
|calendarHeight| dimension |日历的高度
|duration|integer| 日历自动滑动的时间
|isShowLunar| boolean |是否显示农历
|isShowHoliday|boolean| 是否显示法定节假日
|isWeekHold|boolean| 周状态是否固定，默认不固定
|isDefaultSelect|boolean| 是否默认选中（只对单个月日历或者周日历有效）
|defaultCalendar|enum| 默认视图 week 或者 month
|pointLocation|enum| 指示点的文职 up（在公历的上方） 或者 down（在公历的下方） 默认是up
|firstDayOfWeek|enum| 一周开始的星期天还是星期一 sunday 或者 monday 默认是sunday
|holidayLocation|enum| 法定节假日相对公历日期的位置 top_right（右上方）、top_left（左上方）、bottom_right（右下方）、bottom_left（左下方）



## 版本更新
* 3.2.2<br/>增加设置日历初始化日期的方法
* 3.2.1<br/>设置日期区间
* 3.2.0<br/>miui10完美了
* 3.1.5<br/>更正2019年劳动节公休
* 3.1.4<br/>增加toNextPager()和toLastPager()
* 3.1.3<br/>修复Emui日历选中第一行下拉时周日历不消失的bug
* 3.1.2<br/>修复初十的农历日期显示
* 3.1.1<br/>修复当月跳转今天不回调



License
-------


     Copyright 2018 necer

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
   
   

