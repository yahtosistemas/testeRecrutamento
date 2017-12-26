CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    datanascimento date,
    cpf VARCHAR(14),
    funcionario boolean
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE projeto (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE,
	data_previsao_fim DATE,
	data_fim DATE,
	descricao VARCHAR(5000),
	status VARCHAR(45),
	orcamento FLOAT,
	risco VARCHAR(45),
	idgerente BIGINT(20),
    FOREIGN KEY (idgerente) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE membros (
    idprojeto BIGINT(20) NOT NULL,
    idpessoa BIGINT(20) NOT NULL,
    PRIMARY KEY (idprojeto, idpessoa),
    FOREIGN KEY (idpessoa) REFERENCES pessoa(id),
    FOREIGN KEY (idprojeto) REFERENCES projeto(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
