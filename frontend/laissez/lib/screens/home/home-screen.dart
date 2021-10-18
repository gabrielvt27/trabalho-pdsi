import 'package:flutter/material.dart';
import 'package:laissez/models/produto.dart';
import 'package:laissez/screens/home/bottom_navigation.dart';
import 'package:laissez/screens/home/item_list.dart';
import 'search_box.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key key}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  Future<List<Produto>> produtos;

  @override
  void initState() {
    super.initState();
    produtos = fetchProdutos();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomNavigation(),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Center(
                child: Image(
                  image: AssetImage("assets/icons/hat.png"),
                  width: 70,
                  height: 70,
                ),
              ),
              Center(
                child: Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Text(
                    "Uberlândia, MG",
                    style: TextStyle(
                        color: Colors.grey[900],
                        fontWeight: FontWeight.bold,
                        fontSize: 18),
                  ),
                ),
              ),
              SearchBox(
                onChanged: (value) {
                  if (value.isEmpty) {
                    setState(() {
                      produtos = fetchProdutos();
                    });
                  }
                },
                onSubmitted: (value) {
                  setState(() {
                    produtos = fetchProdutos(search: value);
                  });
                },
              ),
              ItemList(produtos: produtos),
            ],
          ),
        ),
      ),
    );
  }
}

Future<List<Produto>> fetchProdutos({String search = ""}) async {
  var response;
  if (search.isEmpty) {
    response = await http.get(Uri.parse('http://10.0.2.2:8080/produto'));
  } else {
    response = await http
        .get(Uri.parse('http://10.0.2.2:8080/produto/search/${search}'));
  }

  if (response.statusCode == 200) {
    String source = Utf8Decoder().convert(response.bodyBytes);
    Iterable list = json.decode(source);
    var produtos = list.map((season) => Produto.fromJson(season)).toList();
    return produtos;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load products');
  }
}
