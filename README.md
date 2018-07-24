# boletim-api
API de Boletim para uso em disciplinas e cursos diversos.

A URL base é `/boletins`. Porém, a API "imperfeita", ou seja, que não segue as boas práticas do REST, tem como base a `/boletinsruim`.


**`GET /boletins/` {header: 'token'}** Todos os boletins do token

**`GET /boletins?aluno=x` {header: 'token'}** Todos os boletins do token cujo aluno contenha o termo em 'aluno' 

**`GET /boletins/{id}` {header: 'token'}** O boletim do token com o 'id' informado

**`DELETE /boletins/{id}` {header: 'token'}** Exclui o boletim do token com o 'id' informado

**`POST /boletins/ {JSON}` {header: 'token'}** Cria um boletim para o token

Exemplo de JSON para **criação**:
```json
{
  "aluno": "Zé Lokão",
  "disciplina": "Geografia",
  "nota1": 7,
  "nota2": 10,
  "frequencia": 75
}
```

Exemplo de JSON chega nas **consultas**:
```json
{
    "id": 3,
    "aluno": "Zé Lokão",
    "disciplina": "Geografia",
    "nota1": 7,
    "nota2": 10,
    "frequencia": 75,
    "token": "yoshi",
    "media": 8.5,
    "resultado": "Aprovado"
}
```

* O header **token** deve conter pelo menos 3 letras e deve ser enviado em todas as requisições
