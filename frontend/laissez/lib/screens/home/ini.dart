import 'package:flutter/material.dart';
import 'package:laissez/screens/home/cad_cliente.dart';
import 'package:laissez/screens/home/choose_usuario.dart';
import 'package:laissez/screens/home/home-screen.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Ini extends StatelessWidget {
  const Ini({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      padding: EdgeInsets.all(30),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Spacer(
              flex: 2,
            ),
            Image(image: AssetImage("assets/icons/hat.png")),
            Spacer(
              flex: 2,
            ),
            Text(
              "Bem Vindo ao Laissez",
              style: TextStyle(
                  fontSize: 32,
                  color: Colors.black,
                  fontWeight: FontWeight.bold),
            ),
            Spacer(
              flex: 1,
            ),
            Text(
              "Liberte-se das filas e dos caixas!",
              style: TextStyle(
                  fontSize: 18,
                  color: Colors.black38,
                  fontWeight: FontWeight.bold),
            ),
            Spacer(
              flex: 1,
            ),
            SizedBox(
              width: double.infinity,
              height: 60,
              child: ElevatedButton(
                onPressed: () async {
                  SharedPreferences prefs =
                      await SharedPreferences.getInstance();
                  bool logado = prefs.getBool('logado') ?? false;
                  print(logado);
                  if (!logado) {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => ChooseUser()),
                    );
                  } else {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => HomeScreen()),
                    );
                  }
                },
                child: Text(
                  "Come??ar",
                  style: TextStyle(fontSize: 20),
                ),
                style: ElevatedButton.styleFrom(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10), // <-- Radius
                  ),
                  primary: Colors.red[400],
                ),
              ),
            ),
            Spacer(
              flex: 1,
            ),
          ],
        ),
      ),
    ));
  }
}
