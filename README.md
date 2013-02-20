android_client
==============

ITAMCO Android Client Application

This application is the first MTConnect Client app for the Android. View your Machine Tools and Controls on any of your Android devices.

Great for Machine Operators, Maintenance Technicians, Engineers, Programmers, or Plant Managers.

Quick and Easy Access to your MTConnect Agent over both Mobile Phone and WiFi Networks.

Add, Delete, and Monitor as many machines and controls as you would like. Just select the desired Device after entering the http:// DNS or ip address and touch the "Current" button on the bottom of the screen and view in real-time your machine or control. Host Connection must be MTConnect compatible (requires MTConnect Agent).

A DOM Parser was developed in the application to parse the XML stream produced by a MTConnect Agent.

Some of contents of this readme can also be found at android.com, please refer to android.com for more info.

Creating an Android Project
PREVIOUSNEXT
THIS LESSON TEACHES YOU TO

Create a Project with Eclipse
Create a Project with Command Line Tools
YOU SHOULD ALSO READ

Installing the SDK
Managing Projects
An Android project contains all the files that comprise the source code for your Android app. The Android SDK tools make it easy to start a new Android project with a set of default project directories and files.

This lesson shows how to create a new project either using Eclipse (with the ADT plugin) or using the SDK tools from a command line.

Note: You should already have the Android SDK installed, and if you're using Eclipse, you should also have the ADT plugin installed (version 21.0.0 or higher). If you don't have these, follow the guide to Installing the Android SDK before you start this lesson.

Create a Project with Eclipse
Click New  in the toolbar.
In the window that appears, open the Android folder, select Android Application Project, and click Next.

Figure 1. The New Android App Project wizard in Eclipse.

Fill in the form that appears:
Application Name is the app name that appears to users. For this project, use "My First App."
Project Name is the name of your project directory and the name visible in Eclipse.
Package Name is the package namespace for your app (following the same rules as packages in the Java programming language). Your package name must be unique across all packages installed on the Android system. For this reason, it's generally best if you use a name that begins with the reverse domain name of your organization or publisher entity. For this project, you can use something like "com.example.myfirstapp." However, you cannot publish your app on Google Play using the "com.example" namespace.
Minimum Required SDK is the lowest version of Android that your app supports, indicated using the API level. To support as many devices as possible, you should set this to the lowest version available that allows your app to provide its core feature set. If any feature of your app is possible only on newer versions of Android and it's not critical to the app's core feature set, you can enable the feature only when running on the versions that support it (as discussed in Supporting Different Platform Versions). Leave this set to the default value for this project.
Target SDK indicates the highest version of Android (also using the API level) with which you have tested with your application.
As new versions of Android become available, you should test your app on the new version and update this value to match the latest API level in order to take advantage of new platform features.
Compile With is the platform version against which you will compile your app. By default, this is set to the latest version of Android available in your SDK. (It should be Android 4.1 or greater; if you don't have such a version available, you must install one using the SDK Manager). You can still build your app to support older versions, but setting the build target to the latest version allows you to enable new features and optimize your app for a great user experience on the latest devices.
Theme specifies the Android UI style to apply for your app. You can leave this alone.
Click Next.
On the next screen to configure the project, leave the default selections and click Next.
The next screen can help you create a launcher icon for your app.
You can customize an icon in several ways and the tool generates an icon for all screen densities. Before you publish your app, you should be sure your icon meets the specifications defined in the Iconography design guide.
Click Next.
Now you can select an activity template from which to begin building your app.
For this project, select BlankActivity and click Next.
Leave all the details for the activity in their default state and click Finish.
Your Android project is now set up with some default files and you’re ready to begin building the app. Continue to the next lesson.

Create a Project with Command Line Tools
If you're not using the Eclipse IDE with the ADT plugin, you can instead create your project using the SDK tools from a command line:

Change directories into the Android SDK’s tools/ path.
Execute:
android list targets
This prints a list of the available Android platforms that you’ve downloaded for your SDK. Find the platform against which you want to compile your app. Make a note of the target id. We recommend that you select the highest version possible. You can still build your app to support older versions, but setting the build target to the latest version allows you to optimize your app for the latest devices.
If you don't see any targets listed, you need to install some using the Android SDK Manager tool. See Adding Platforms and Packages.
Execute:
android create project --target <target-id> --name MyFirstApp \
--path <path-to-workspace>/MyFirstApp --activity MainActivity \
--package com.example.myfirstapp
Replace <target-id> with an id from the list of targets (from the previous step) and replace <path-to-workspace> with the location in which you want to save your Android projects.
Your Android project is now set up with several default configurations and you’re ready to begin building the app. Continue to the next lesson.

Tip: Add the platform-tools/ as well as the tools/ directory to your PATH environment variable.

Running Your App
PREVIOUSNEXT
THIS LESSON TEACHES YOU TO

Run on a Real Device
Run on the Emulator
YOU SHOULD ALSO READ

Using Hardware Devices
Managing Virtual Devices
Managing Projects
If you followed the previous lesson to create an Android project, it includes a default set of "Hello World" source files that allow you to immediately run the app.

How you run your app depends on two things: whether you have a real Android-powered device and whether you're using Eclipse. This lesson shows you how to install and run your app on a real device and on the Android emulator, and in both cases with either Eclipse or the command line tools.

Before you run your app, you should be aware of a few directories and files in the Android project:

AndroidManifest.xml
The manifest file describes the fundamental characteristics of the app and defines each of its components. You'll learn about various declarations in this file as you read more training classes.
One of the most important elements your manifest should include is the <uses-sdk> element. This declares your app's compatibility with different Android versions using the android:minSdkVersion and android:targetSdkVersion attributes. For your first app, it should look like this:
<manifest xmlns:android="http://schemas.android.com/apk/res/android" ... >
    <uses-sdk android:minSdkVersion="8" android:targetSdkVersion="17" />
    ...
</manifest>
You should always set the android:targetSdkVersion as high as possible and test your app on the corresponding platform version. For more information, read Supporting Different Platform Versions.
src/
Directory for your app's main source files. By default, it includes an Activity class that runs when your app is launched using the app icon.
res/
Contains several sub-directories for app resources. Here are just a few:
drawable-hdpi/
Directory for drawable objects (such as bitmaps) that are designed for high-density (hdpi) screens. Other drawable directories contain assets designed for other screen densities.
layout/
Directory for files that define your app's user interface.
values/
Directory for other various XML files that contain a collection of resources, such as string and color definitions.
When you build and run the default Android app, the default Activity class starts and loads a layout file that says "Hello World." The result is nothing exciting, but it's important that you understand how to run your app before you start developing.

Run on a Real Device
If you have a real Android-powered device, here's how you can install and run your app:

Plug in your device to your development machine with a USB cable. If you're developing on Windows, you might need to install the appropriate USB driver for your device. For help installing drivers, see the OEM USB Drivers document.
Enable USB debugging on your device.
On most devices running Android 3.2 or older, you can find the option under Settings > Applications > Development.
On Android 4.0 and newer, it's in Settings > Developer options.
Note: On Android 4.2 and newer, Developer options is hidden by default. To make it available, go to Settings > About phone and tap Build number seven times. Return to the previous screen to find Developer options.
To run the app from Eclipse:

Open one of your project's files and click Run  from the toolbar.
In the Run as window that appears, select Android Application and click OK.
Eclipse installs the app on your connected device and starts it.

Or to run your app from a command line:

Change directories to the root of your Android project and execute:
ant debug
Make sure the Android SDK platform-tools/ directory is included in your PATH environment variable, then execute:
adb install bin/MyFirstApp-debug.apk
On your device, locate MyFirstActivity and open it.
That's how you build and run your Android app on a device! To start developing, continue to the next lesson.

Run on the Emulator
Whether you're using Eclipse or the command line, to run your app on the emulator you need to first create an Android Virtual Device (AVD). An AVD is a device configuration for the Android emulator that allows you to model different devices.


Figure 1. The AVD Manager showing a few virtual devices.

To create an AVD:

Launch the Android Virtual Device Manager:
In Eclipse, click Android Virtual Device Manager  from the toolbar.
From the command line, change directories to <sdk>/tools/ and execute:
android avd
In the Android Virtual Device Manager panel, click New.
Fill in the details for the AVD. Give it a name, a platform target, an SD card size, and a skin (HVGA is default).
Click Create AVD.
Select the new AVD from the Android Virtual Device Manager and click Start.
After the emulator boots up, unlock the emulator screen.
To run the app from Eclipse:

Open one of your project's files and click Run  from the toolbar.
In the Run as window that appears, select Android Application and click OK.
Eclipse installs the app on your AVD and starts it.

Or to run your app from the command line:

Change directories to the root of your Android project and execute:
ant debug
Make sure the Android SDK platform-tools/ directory is included in your PATH environment variable, then execute:
adb install bin/MyFirstApp-debug.apk
On the emulator, locate MyFirstActivity and open it.
That's how you build and run your Android app on the emulator! To start developing, continue to the next lesson.

