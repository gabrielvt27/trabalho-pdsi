import 'package:flutter/material.dart';
import 'package:laissez/screens/home/bottom_navigation.dart';
import 'package:laissez/screens/home/ini.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Perfil extends StatefulWidget {
  const Perfil({Key key}) : super(key: key);

  @override
  _PerfilState createState() => _PerfilState();
}

class _PerfilState extends State<Perfil> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomNavigation(),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              children: [
                SizedBox(
                  width: double.infinity,
                  height: 60,
                  child: ElevatedButton(
                    onPressed: () async {
                      SharedPreferences prefs =
                          await SharedPreferences.getInstance();
                      prefs.setBool('logado', false);
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => Ini()),
                      );
                    },
                    child: Row(
                      children: [
                        Icon(
                          Icons.logout,
                          color: Colors.green,
                        ),
                        SizedBox(
                          width: MediaQuery.of(context).size.width / 3.3,
                        ),
                        Text(
                          "Sair",
                          style: TextStyle(
                            fontSize: 20,
                            color: Colors.green,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                    style: ElevatedButton.styleFrom(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10), // <-- Radius
                      ),
                      primary: Colors.grey[200],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
