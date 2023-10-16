import 'package:flutter/material.dart';

void main() {
  runApp(App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text("Hello"),
            centerTitle: true,
          ),
          backgroundColor: Colors.yellow.shade100,
          body: Padding(
            padding: EdgeInsets.symmetric(
              horizontal: 20,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  children: [
                    Text("Left"),
                  ],
                ),
                Column(
                  children: [
                    Text('Right'),
                  ],
                )
              ],
            ),
          )),
    );
  }
}
