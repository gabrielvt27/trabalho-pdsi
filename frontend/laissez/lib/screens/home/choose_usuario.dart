import 'package:flutter/material.dart';
import 'package:laissez/screens/home/cad_cliente.dart';
import 'package:laissez/screens/home/cad_dono.dart';
import 'package:laissez/screens/login/login_screen.dart';

class ChooseUser extends StatelessWidget {
  const ChooseUser({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Center(
                child: Padding(
                  padding: const EdgeInsets.only(bottom: 30, top: 30),
                  child: Image(
                    image: AssetImage("assets/icons/hat.png"),
                    width: 100,
                    height: 100,
                  ),
                ),
              ),
              RichText(
                text: TextSpan(
                  text: "Você é um ",
                  style: TextStyle(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                  ),
                  children: [
                    TextSpan(
                      text: "cliente",
                      style: TextStyle(
                        color: Colors.red[400],
                      ),
                    ),
                    TextSpan(
                      text: " ou ",
                    ),
                    TextSpan(
                      text: "dono de supermercado?",
                      style: TextStyle(
                        color: Colors.red[400],
                      ),
                    ),
                  ],
                ),
              ),
              SizedBox(
                width: double.infinity,
                height: 60,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => CadastrarCliente()),
                    );
                  },
                  child: Text(
                    "Sou Cliente",
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
              SizedBox(
                width: double.infinity,
                height: 60,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => CadastrarDono()),
                    );
                  },
                  child: Text(
                    "Sou dono",
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
              InkWell(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => LoginScreen()),
                  );
                },
                child: Align(
                  alignment: Alignment.bottomCenter,
                  child: RichText(
                    text: TextSpan(
                      text: "Já possui uma conta?",
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                      children: [
                        TextSpan(
                          text: " Efetuar Login",
                          style: TextStyle(
                            color: Colors.green,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
