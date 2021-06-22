import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:laissez/constants.dart';

class SearchBox extends StatelessWidget {
  final ValueChanged<String> onSubmitted;
  final ValueChanged<String> onChanged;
  const SearchBox({
    Key key,
    this.onSubmitted,
    this.onChanged,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(20),
      padding: EdgeInsets.symmetric(horizontal: 25, vertical: 5),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        border: Border.all(
          color: ksecondaryColor.withOpacity(0.32),
        ),
      ),
      child: TextField(
        onSubmitted: onSubmitted,
        onChanged: onChanged,
        decoration: InputDecoration(
          labelStyle: TextStyle(fontSize: 10),
          border: InputBorder.none,
          icon: SvgPicture.asset(
            "assets/icons/search.svg",
            color: Colors.black,
          ),
          hintText: "Pesquisar produtos",
          hintStyle: TextStyle(color: Colors.black),
        ),
      ),
    );
  }
}
