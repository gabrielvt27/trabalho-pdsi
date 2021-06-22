import 'package:flutter/material.dart';
import 'package:laissez/constants.dart';
import 'package:laissez/models/produto.dart';
import 'package:http/http.dart' as http;

class ItemList extends StatelessWidget {
  const ItemList({
    Key key,
    @required this.produtos,
  }) : super(key: key);

  final Future produtos;

  Future<String> addProductCart(int idproduto) async {
    var response = await http.put(Uri.parse(
        'http://10.0.2.2:8080/carrinho/add/${kIdCarrinho}/${idproduto}'));

    if (response.statusCode == 200) {
      return "Item Adicionado ao Carrinho";
    } else {
      throw Exception('Failed to load products');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: FutureBuilder<List<Produto>>(
        future: produtos,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return GridView.builder(
              physics: BouncingScrollPhysics(),
              scrollDirection: Axis.vertical,
              shrinkWrap: true,
              itemCount: snapshot.data.length,
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                crossAxisSpacing: 2.0,
                mainAxisSpacing: 2.0,
              ),
              itemBuilder: (context, index) {
                return GridTile(
                    child: Card(
                        elevation: 10,
                        child: Column(
                          children: [
                            Image.network(
                              snapshot.data[index].imagem,
                              width: 100,
                              height: 100,
                            ),
                            Padding(
                              padding: const EdgeInsets.only(left: 8, top: 8),
                              child: Row(
                                children: [
                                  Text(
                                    "${snapshot.data[index].nome}",
                                    style:
                                        TextStyle(fontWeight: FontWeight.bold),
                                    textAlign: TextAlign.start,
                                  ),
                                ],
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.fromLTRB(8, 5, 8, 0),
                              child: Row(
                                children: [
                                  Text(
                                    "R\$${snapshot.data[index].preco.toString().replaceAll(".", ",")}",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontSize: 18,
                                        fontWeight: FontWeight.bold),
                                  ),
                                  Spacer(),
                                  ElevatedButton(
                                      onPressed: () {
                                        var resp = addProductCart(
                                            snapshot.data[index].idproduto);
                                        resp.then((value) => {
                                              ScaffoldMessenger.of(context)
                                                  .showSnackBar(SnackBar(
                                                      content: Text(value)))
                                            });
                                      },
                                      style: ElevatedButton.styleFrom(
                                          shape: RoundedRectangleBorder(
                                            borderRadius: BorderRadius.circular(
                                                10), // <-- Radius
                                          ),
                                          primary: Colors.green),
                                      child: Icon(Icons.add))
                                ],
                              ),
                            )
                          ],
                        )));
              },
            );
          } else if (snapshot.hasError) {
            return Text("${snapshot.error}");
          }

          return Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
