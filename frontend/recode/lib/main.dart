import 'package:flutter/material.dart';
import 'package:recode/create_feed.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: CreateFeed(),
    );
  }
}
