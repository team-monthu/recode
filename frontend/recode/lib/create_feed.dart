import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:markdown_widget/markdown_widget.dart';

class CreateFeed extends StatefulWidget {
  @override
  _CreateFeedState createState() => _CreateFeedState();
}

class _CreateFeedState extends State<CreateFeed>
    with SingleTickerProviderStateMixin {
  TextEditingController? _textController;

  String text = "";

  @override
  void initState() {
    super.initState();
    _textController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
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
            Flexible(
              flex: 5,
              child: Container(
                child: Padding(
                  padding: EdgeInsets.symmetric(vertical: 10, horizontal: 10),
                  child: TextField(
                    controller: _textController,
                    minLines: null,
                    maxLines: null,
                    keyboardType: TextInputType.multiline,
                    expands: true,
                    textAlignVertical: TextAlignVertical.top,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: 'Input here..',
                    ),
                    onChanged: (String text) {
                      setState(() {
                        this.text = text;
                      });
                    },
                  ),
                ),
              ),
            ),
            Flexible(
              flex: 5,
              child: Container(
                child: Padding(
                  padding: EdgeInsets.symmetric(vertical: 10, horizontal: 10),
                  child: Container(
                    decoration: BoxDecoration(
                      border: Border.all(color: Color.fromRGBO(0, 0, 0, 0.4)),
                      borderRadius: BorderRadius.all(Radius.circular(4)),
                    ),
                    child: Padding(
                      padding: EdgeInsets.all(10),
                      child: MarkdownWidget(data: text),
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
