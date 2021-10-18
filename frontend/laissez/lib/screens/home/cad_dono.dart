import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:laissez/models/usuario.dart';
import 'package:laissez/screens/home/cad_dono_prosseguir.dart';
import 'package:laissez/screens/home/home-screen.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CadastrarDono extends StatefulWidget {
  const CadastrarDono({Key key}) : super(key: key);

  @override
  _CadastrarState createState() => _CadastrarState();
}

class _CadastrarState extends State<CadastrarDono> {
  final _formKey = GlobalKey<FormState>();
  final fieldNomeController = TextEditingController();
  final fieldEmailController = TextEditingController();
  final fieldSenhaController = TextEditingController();
  String nome, email, senha;

  void clearText() {
    fieldNomeController.clear();
    fieldEmailController.clear();
    fieldSenhaController.clear();
  }

  Usuario cadastrar({String nome, String email, String senha}) {
    return Usuario(nome: nome, email: email, senha: senha);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          child: Form(
            key: _formKey,
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(bottom: 80, top: 30),
                    child: Image(
                      image: AssetImage("assets/icons/hat.png"),
                      width: 100,
                      height: 100,
                    ),
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      "Cadastro - dono",
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 30,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      "Insira suas credenciais para continuar",
                      style: TextStyle(
                        color: Color.fromRGBO(124, 124, 124, 1),
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  TextFormField(
                    decoration: InputDecoration(hintText: 'Nome Completo'),
                    controller: fieldNomeController,
                    onSaved: (val) {
                      nome = val;
                    },
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  TextFormField(
                    decoration: InputDecoration(hintText: 'Email'),
                    controller: fieldEmailController,
                    keyboardType: TextInputType.emailAddress,
                    onSaved: (val) {
                      email = val;
                    },
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  TextFormField(
                    decoration: InputDecoration(hintText: 'Senha'),
                    controller: fieldSenhaController,
                    keyboardType: TextInputType.text,
                    obscureText: true,
                    onSaved: (val) {
                      senha = val;
                    },
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  RichText(
                    text: TextSpan(
                      text: "Ao continuar, você concorda com nossos",
                      style: TextStyle(
                        color: Color.fromRGBO(124, 124, 124, 1),
                        fontWeight: FontWeight.bold,
                      ),
                      children: [
                        TextSpan(
                          text: " Termos de Serviço",
                          style: TextStyle(color: Colors.green),
                        ),
                        TextSpan(
                          text: " e ",
                          style: TextStyle(
                            color: Color.fromRGBO(124, 124, 124, 1),
                          ),
                        ),
                        TextSpan(
                          text: "Política de Privacidade",
                          style: TextStyle(
                            color: Colors.green,
                          ),
                        ),
                      ],
                    ),
                  ),
                  Divider(
                    color: Colors.transparent,
                  ),
                  SizedBox(
                    width: double.infinity,
                    height: 60,
                    child: ElevatedButton(
                      onPressed: () {
                        _formKey.currentState.save();
                        Usuario retorno =
                            cadastrar(nome: nome, email: email, senha: senha);
                        Navigator.pushAndRemoveUntil(
                          context,
                          MaterialPageRoute(
                            builder: (context) => CadastrarDonoProsseguir(
                              usuario: retorno,
                            ),
                          ),
                          (route) => false,
                        );
                      },
                      child: Text(
                        "Prosseguir",
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
                  Divider(
                    color: Colors.transparent,
                  ),
                  RichText(
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
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
