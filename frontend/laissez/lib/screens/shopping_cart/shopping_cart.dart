import 'package:flutter/material.dart';
import 'package:laissez/constants.dart';
import 'package:laissez/models/produto.dart';
import 'package:laissez/screens/home/bottom_navigation.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';

import 'package:laissez/screens/home/final_screen.dart';

class ShoppingCart extends StatefulWidget {
  const ShoppingCart({Key key}) : super(key: key);

  @override
  _ShoppingCartState createState() => _ShoppingCartState();
}

class _ShoppingCartState extends State<ShoppingCart> {
  Map<String, dynamic> dados;
  List<dynamic> produtos = [];
  double total;

  Future<Map<String, dynamic>> fetchProdutosCarrinho() async {
    var response = await http
        .get(Uri.parse('http://10.0.2.2:8080/carrinho/search/${kIdCarrinho}'));

    if (response.statusCode == 200) {
      String source = Utf8Decoder().convert(response.bodyBytes);
      Map<String, dynamic> data = json.decode(source);
      return data;
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load products');
    }
  }

  Future<String> addProductCart(int idproduto) async {
    var response = await http.put(Uri.parse(
        'http://10.0.2.2:8080/carrinho/add/${kIdCarrinho}/${idproduto}'));

    if (response.statusCode == 200) {
      return "OK";
    } else {
      throw Exception('Failed to load products');
    }
  }

  Future<String> removeOneProductCart(int idproduto) async {
    var response = await http.delete(Uri.parse(
        'http://10.0.2.2:8080/carrinho/removeone/${kIdCarrinho}/${idproduto}'));

    if (response.statusCode == 200) {
      return "OK";
    } else {
      throw Exception('Failed to load products');
    }
  }

  Future<String> removeProductCart(int idproduto) async {
    var response = await http.delete(Uri.parse(
        'http://10.0.2.2:8080/carrinho/remove/${kIdCarrinho}/${idproduto}'));

    if (response.statusCode == 200) {
      return "OK";
    } else {
      throw Exception('Failed to load products');
    }
  }

  Future<String> finalizarCompra() async {
    var response = await http.delete(
        Uri.parse('http://10.0.2.2:8080/carrinho/removeall/${kIdCarrinho}'));

    if (response.statusCode == 200) {
      return "OK";
    } else {
      throw Exception('Failed to load products');
    }
  }

  void getProddutosCarrinho() async {
    dados = await fetchProdutosCarrinho();
    setState(() {
      total = dados["total"];
      produtos =
          dados["produtos"].map((season) => Produto.fromJson(season)).toList();
    });
  }

  @override
  void initState() {
    super.initState();
    getProddutosCarrinho();
    /*produtos = dados["produtos"];
    total = dados["total"];*/
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.white,
          elevation: 1,
          title: Text("Meu Carrinho"),
          centerTitle: true,
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        floatingActionButton: Visibility(
            visible: (produtos.length == 0) ? false : true,
            child: FloatingActionButton.extended(
              onPressed: () async {
                var resp = await finalizarCompra();
                if (resp == "OK") {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => FinalScreen()),
                  );
                  getProddutosCarrinho();
                }
              },
              label: RichText(
                  text: TextSpan(children: [
                TextSpan(
                    text: "Finalizar Compra\t",
                    style:
                        TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                TextSpan(
                    text: "R\$${total.toString().replaceAll(".", ",")}",
                    style: TextStyle(
                        fontSize: 14,
                        backgroundColor: Colors.green[700],
                        fontWeight: FontWeight.bold))
              ])),
              backgroundColor: Colors.green,
            )),
        bottomNavigationBar: BottomNavigation(),
        body: (produtos.length > 0)
            ? Container(
                color: Colors.transparent,
                margin: EdgeInsets.only(bottom: 65),
                child: ListView.builder(
                    itemCount: produtos.length,
                    itemBuilder: (context, index) {
                      return Container(
                        height: 150,
                        child: Card(
                          child: Row(
                            children: [
                              Expanded(
                                flex: 33,
                                child: Image.network(
                                  produtos[index].imagem,
                                ),
                              ),
                              Expanded(
                                flex: 66,
                                child: Padding(
                                  padding: const EdgeInsets.all(10.0),
                                  child: Column(
                                    crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                    children: [
                                      Expanded(
                                        flex: 50,
                                        child: Row(
                                          children: [
                                            Padding(
                                              padding: const EdgeInsets.only(
                                                  top: 20),
                                              child: Text(
                                                produtos[index].nome,
                                                style: TextStyle(
                                                    color: Colors.black,
                                                    fontWeight: FontWeight.bold,
                                                    fontSize: 18),
                                              ),
                                            ),
                                            Spacer(),
                                            IconButton(
                                              icon: Icon(Icons.close),
                                              onPressed: () async {
                                                var resp =
                                                    await removeProductCart(
                                                        produtos[index]
                                                            .idproduto);
                                                if (resp == "OK") {
                                                  getProddutosCarrinho();
                                                }
                                              },
                                            )
                                          ],
                                        ),
                                      ),
                                      Expanded(
                                          flex: 50,
                                          child: Center(
                                            child: Row(
                                              children: [
                                                ElevatedButton(
                                                    onPressed: () async {
                                                      var resp =
                                                          await removeOneProductCart(
                                                              produtos[index]
                                                                  .idproduto);
                                                      if (resp == "OK") {
                                                        getProddutosCarrinho();
                                                      }
                                                    },
                                                    style: ElevatedButton
                                                        .styleFrom(
                                                            shape:
                                                                RoundedRectangleBorder(
                                                              borderRadius:
                                                                  BorderRadius
                                                                      .circular(
                                                                          10), // <-- Radius
                                                            ),
                                                            primary:
                                                                Colors.white),
                                                    child: Icon(
                                                      Icons.remove,
                                                      color: Colors.grey,
                                                    )),
                                                Spacer(),
                                                Text(
                                                    "${produtos[index].quantidade.toString()}",
                                                    style: TextStyle(
                                                        color: Colors.black,
                                                        fontWeight:
                                                            FontWeight.bold)),
                                                Spacer(),
                                                ElevatedButton(
                                                    onPressed: () async {
                                                      var resp =
                                                          await addProductCart(
                                                              produtos[index]
                                                                  .idproduto);
                                                      if (resp == "OK") {
                                                        getProddutosCarrinho();
                                                      }
                                                    },
                                                    style: ElevatedButton
                                                        .styleFrom(
                                                            shape:
                                                                RoundedRectangleBorder(
                                                              borderRadius:
                                                                  BorderRadius
                                                                      .circular(
                                                                          10), // <-- Radius
                                                            ),
                                                            primary:
                                                                Colors.white),
                                                    child: Icon(
                                                      Icons.add,
                                                      color: Colors.green,
                                                    )),
                                                Spacer(),
                                                Text(
                                                  "R\$${produtos[index].preco.toString().replaceAll(".", ",")}",
                                                  style: TextStyle(
                                                      color: Colors.black,
                                                      fontWeight:
                                                          FontWeight.bold),
                                                ),
                                              ],
                                            ),
                                          )),
                                    ],
                                  ),
                                ),
                              )
                            ],
                          ),
                        ),
                      );
                    }),
              )
            : Center(
                child: RichText(
                    textAlign: TextAlign.center,
                    text: TextSpan(children: [
                      TextSpan(
                          text: "Nenhum item no carrinho\n\n",
                          style: TextStyle(
                              fontSize: 25,
                              fontWeight: FontWeight.bold,
                              color: Colors.grey)),
                      TextSpan(
                          text: ":(",
                          style: TextStyle(
                              fontSize: 30,
                              fontWeight: FontWeight.bold,
                              color: Colors.grey))
                    ]))));
  }
}
