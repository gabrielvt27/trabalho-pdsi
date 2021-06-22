import 'package:flutter/material.dart';
import 'package:laissez/constants.dart';
import 'package:laissez/screens/home/final_screen.dart';
import 'package:laissez/screens/home/home-screen.dart';
import 'package:laissez/screens/home/ini.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Laissez',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          textTheme: TextTheme(
              body1: TextStyle(color: ksecondaryColor),
              body2: TextStyle(color: ksecondaryColor)),
          primaryColor: kPrimaryColor,
          scaffoldBackgroundColor: Colors.white),
      home: Ini(),
    );
  }
}
