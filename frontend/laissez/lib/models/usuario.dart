class Usuario {
  int idusuario;
  String nome;
  String email;
  String senha;
  String cnpj;
  String endereco;
  String plano;

  Usuario({
    this.idusuario,
    this.nome,
    this.email,
    this.senha,
    this.cnpj,
    this.endereco,
    this.plano,
  });

  Map<String, dynamic> toMap() {
    return {
      'idusuario': idusuario,
      'nome': nome,
      'enderecoemail': email,
      'senha': senha,
      'cnpj': cnpj,
      'endereco': endereco,
      'plano': plano,
    };
  }
}
