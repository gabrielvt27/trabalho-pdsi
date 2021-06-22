class Produto {
  final int idproduto;
  final String nome;
  final String marca;
  final String descricao;
  final String tipo;
  final double preco;
  final String imagem;
  final int qrcode;
  final int quantidade;

  Produto({
    this.idproduto,
    this.nome,
    this.marca,
    this.descricao,
    this.tipo,
    this.preco,
    this.imagem,
    this.qrcode,
    this.quantidade,
  });

  factory Produto.fromJson(Map<String, dynamic> json) {
    return Produto(
      idproduto: json['idproduto'],
      nome: json['nome'],
      marca: json['marca'],
      descricao: json['descricao'],
      tipo: json['tipo'],
      preco: json['preco'],
      imagem: json['imagem'],
      qrcode: json['qrcode'],
      quantidade: json['quantidade'],
    );
  }
}
