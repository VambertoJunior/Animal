package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 12) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                val cachorro = Cachorro(10, Cor.PRETO)
                cachorro.nome = "Rex"
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                val gato = Gato(5, Cor.MARROM)
                gato.nome = "Felix"
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                val passaro = Passaro(2, Cor.VERMELHO)
                passaro.nome = "Piu"
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            6 -> {
                println("Digite nome do animal deseja remover:")
                val nome = readLine() ?: ""
                val animalRemovido = repositorioAnimal.removerAnimal(nome)
                if (animalRemovido != null){
                    println("$nome foi removido sucesso.")
                } else {
                    println("nao foi encontrado animal o nome $nome.")
                }
            }
            7 -> {
                println("Digite a cor que deseja listar:")
                val corString: String? = readLine()
                if (!corString.isNullOrEmpty()) {
                    try {
                        val corEnum: Cor = Cor.valueOf(corString.toLowerCase().capitalize())
                        repositorioAnimal.listarAnimaisCor(corEnum)
                    } catch (e: IllegalArgumentException) {
                        println("Cor inválida")
                    }
                } else {
                    println("Cor inválida")
                    }
                }
            8 -> {
                println("Digite idade deseja listar:")
                val idade = readLine()?.toIntOrNull()
                if (idade != null){
                    repositorioAnimal.listarAnimaisIdade(idade)
                } else {
                    println("Idade inválida")
                }
            }
            9 -> {
                println("Digite nome animal deseja buscar:")
                val nome = readLine() ?: ""
                val animal = repositorioAnimal.buscarAnimalNome(nome)
                if (animal != null){
                    println("Animal encontrado: ${animal.nome}")
                } else {
                    println("Animal não encontrado")
                }
            }
            10 -> {
                val homem = Homem(18, Cor.PRETO)
                homem.nome = "Guga"
                repositorioAnimal.adicionar(homem)
            }
        }
    }
}

enum class Cor {
    BRANCO,
    PRETO,
    CINZA,
    MARROM,
    AMARELO,
    LARANJA,
    VERMELHO,
    AZUL,
    VERDE
}

abstract class Animal(var idade: Int, var cor: Cor) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int{
        return idade * 7
    }
}

class Homem(idade: Int, cor: Cor): Animal(idade, cor){
    override fun emitirSom() {
        println("Olááááááááá")
    }
    override fun idadeEmAnosHumanos(): Int {
        return idade
    }
}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

fun menu() {
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Remover Animal")
    println("7 - Listar cor")
    println("8 - Listar idade")
    println("9 - Buscar animal nome")
    println("10 - Adicionar Homem")
    println("11 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun removerAnimal(nome: String): Animal?{
        for (animal in animais){
            if (animal.nome == nome){
                animais.remove(animal)
                return animal
            }
        }
        return null
    }

    fun listarAnimaisCor(cor: Cor) {
        val animaisPorCor = animais.filter { it.cor == cor }
        if (animaisPorCor.isNotEmpty()) {
            println("Animais da cor $cor:")
            animaisPorCor.forEach { println(it.nome) }
        } else {
            println("Não há animais da cor $cor.")
        }
    }

    fun listarAnimaisIdade(idade: Int) {
        val animaisPorIdade = animais.filter { it.idade == idade }
        if (animaisPorIdade.isNotEmpty()) {
            println("Animais com $idade anos de idade:")
            animaisPorIdade.forEach { println(it.nome) }
        } else {
            println("Não há animais com $idade anos de idade.")
        }
    }

    fun buscarAnimalNome(nome: String): Animal?{
        return animais.find { it.nome == nome }
    }
}