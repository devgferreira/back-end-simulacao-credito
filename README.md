# Projeto para a simulação de liberação de um cartão de crédito

---

## Concepção das APIs
A concepção das APIs adotou os preceitos fundamentais da Clean Architecture, aliados à filosofia do clean code.

O cerne deste projeto reside na integração dos microservices e como eles vão se comunicar entre si. De forma simplificada, a estrutura do projeto é construída em torno da API ms-avliador-credito, estabelecendo uma comunicação precisa com as outras APIs.

Tal abordagem possibilita que um cliente faça a solitação de um cartão de crédito.

## Ferramentas usadas
RabbitMQ: Sistema de mensageria.

Docker: Sistema de contenerização.

Keyclock: Sistema de segurança para a liberação de acesso das API.

Java 17: Linguagem de programação.

Recomendo fortemente que você tenha essas ferramentas instaladas.


## Como iniciar as APIs
Você precisa ter instalado as ferramentas mencionadas no tópico anterior. 

Uso de uma IDE: Vs Code, Eclipse, Intellij, ou um console caso for usar o Docker.

Caso você vá testar localmente, verifique se os perfis do application.yml estão apontando para o LOCAL. Se for usar o Docker, aponte para o DOCKER.

RabbitMQ: Crie as fila de mensageria no RabbitMQ: emissao-cartoes

Keyclock: Crie o realms que está disponível aqui.

Essas ferramentas e instruções irão ajudá-lo a configurar e executar as APIs de forma eficiente.

# Estrutura do Projeto
## Apresentação
Controlador: Controladores de API, responsáveis por receber solicitações e enviar respostas.

## Aplicação
DTOs: Objetos de Transferência de Dados usados para passar dados entre camadas.

Interfaces: Contratos para os serviços.

Serviços: Contém lógica de negócios de alto nível e chama métodos do repositório.

## Domínio
Entidade/Modelo: Entidades de domínio.

Interfaces: Contratos para os Repositórios.

Enums: Enumerações usadas em entidades e/ou regras de negócio.

## Infra
Config: Configuração de dependência do projeto.

Clients: Configuração dos controladores do FeingClient.

Constants: Regras que os valores não podem ser mutáveis.

Exceptions: Configuração de exceção e seu manipulador.

Mqueue: Configuração rabbit para o envio das mensagens.

# Microservices

## Eureka-server
A API eureka-server é um registrador de outras APIs.


## Ms-cloud-geteway
A API ms-cloud-geteway é um centralizador de acesso, todas as APIs que se registrarem na API eureka-server terão a mesma porta por conta da API ms-cloud-geteway.

## Ms-clientes
A API ms-clientes foi feita para a criação e busca de clientes, e consiste com um controlador: ClientesController.

ClientesController:
O ClientesController tem dois end-points.

save: Esse end-point é responsável por criar um novo cliente e persistir o mesmo no banco de dados.
dadosCliente: Esse end-point é responsável por buscar um funcionário pelo seu CPF no banco de dados.
