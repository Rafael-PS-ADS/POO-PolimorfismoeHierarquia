# ⏰ Hierarquia de Classes para Relógios Globais 🌍

Uma estrutura hierárquica de classes foi implementada em Java para representar relógios para diferentes regiões, especificamente formatos de hora Brasileira (BRL) 🇧🇷 e Americana (US) 🇺🇸.

### Hierarquia de Classes 🌲

A classe base é `Clock`, uma `abstract sealed class` que define as propriedades e comportamentos comuns de um relógio. Ela inclui:

* **Propriedades**: `hour`, `minute` e `second` como inteiros protegidos.
* **Getters e Setters**:
    * `setHour(int hour)`: Define a hora, garantindo que não exceda 24. Se `hour` for 24 ou maior, ele define `this.hour` como 24.
    * `setMinute(int minute)`: Define o minuto, garantindo que não exceda 60. Se `minute` for 60 ou maior, ele define `this.minute` como 60.
    * `setSecond(int second)`: Define o segundo, garantindo que não exceda 60. Se `second` for 60 ou maior, ele define `this.second` como 60.
* **Método `getTime()`**: Retorna a hora no formato "HH:MM:SS". ⌚
* **Método `abstract Clock convert(Clock clock)`**: Um método abstrato que deve ser implementado pelas subclasses para lidar com conversões de tempo entre diferentes tipos de relógio. 🔄

A classe `Clock` é `sealed`, o que significa que apenas `BRLClock` e `USClock` são permitidas estendê-la. 🔐

### Implementações ✨

#### `BRLClock` (Relógio Brasileiro) 🇧🇷

A classe `BRLClock` estende `Clock` e representa o tempo no formato de 24 horas comum no Brasil.

* **Método `convert(final Clock clock)`**: Este método sobrescreve o método abstrato `convert` da classe `Clock`. Ele recebe qualquer objeto `Clock` como entrada e define suas próprias propriedades `second` e `minute` diretamente do relógio de entrada. Para a `hour`, ele usa uma expressão `switch` com `pattern matching`:
    * Se o `clock` de entrada for um `USClock`, ele converte a hora US (AM/PM) para um formato de 24 horas. Se o `periodIndicator` for "PM", ele adiciona 12 à hora US; caso contrário, ele usa a hora US diretamente.
    * Se o `clock` de entrada for um `BRLClock`, ele usa diretamente a hora do `BRLClock`.

#### `USClock` (Relógio Americano) 🇺🇸

A classe `USClock` estende `Clock` e representa o tempo no formato de 12 horas com indicadores AM/PM usados nos Estados Unidos.

* **Propriedades**: `periodIndicator` (String) para armazenar "AM" ou "PM". 🌅🌃
* **Getters e Setters**:
    * `getPeriodIndicator()`: Retorna o `periodIndicator`.
    * `setAfterMidDay()`: Define `periodIndicator` como "PM".
    * `setBeforeMidDay()`: Define `periodIndicator` como "AM".
    * `setHour(int hour)`: Sobrescreve o método `setHour` para lidar com a conversão para o formato de 12 horas.
        * Se o `hour` de entrada estiver entre 12 e 23 (inclusive), ele define o `periodIndicator` como "PM" e converte a hora subtraindo 12.
        * Se o `hour` de entrada for 24 ou maior, ele define o `periodIndicator` como "AM" e define `this.hour` como 0 (representando meia-noite).
        * Caso contrário, ele define o `periodIndicator` como "AM" e usa diretamente o `hour` de entrada.
* **Método `convert(final Clock clock)`**: Este método sobrescreve o método abstrato `convert`. Ele define suas próprias propriedades `second` e `minute` a partir do `clock` de entrada. Similar ao `BRLClock`, ele usa uma expressão `switch` para a conversão de `hour`:
    * Se o `clock` de entrada for um `USClock`, ele define diretamente `this.hour` e `periodIndicator` do `USClock` de entrada.
    * Se o `clock` de entrada for um `BRLClock`, ele chama seu próprio método `setHour` com a hora do `BRLClock`, que lida com a conversão de 24 horas para 12 horas e define o `periodIndicator`.
* **Método `getTime()`**: Sobrescreve o método `getTime()` de `Clock` para incluir o `periodIndicator` no formato "HH:MM:SS AM/PM".

### Classe `Main` 🚀

A classe `Main` demonstra o uso da hierarquia de relógios.

* Ela cria um objeto `Scanner` para obter a entrada do usuário para hora, minuto e segundo. ⌨️
* Ela instancia um objeto `BRLClock` e define seu tempo com base na entrada do usuário.
* Em seguida, ela imprime o tempo no formato BRL.
* Finalmente, ela cria um objeto `USClock`, converte o tempo do `BRLClock` para o formato US usando o método `convert`, e imprime o tempo no formato US.

### Como Compilar e Executar ▶️

1.  **Salve os arquivos**: Salve cada bloco de código em seu respectivo arquivo `.java` (`Main.java`, `Clock.java`, `BRLClock.java`, `USClock.java`). 💾
2.  **Compile**: Abra um terminal ou prompt de comando, navegue até o diretório onde você salvou os arquivos e compile-os usando `javac`:
    ```bash
    javac Main.java Clock.java BRLClock.java USClock.java
    ```
    ou simplesmente
    ```bash
    javac *.java
    ```
3.  **Execute**: Após a compilação bem-sucedida, execute a classe `Main`:
    ```bash
    java Main
    ```
    O programa solicitará que você insira a hora, minuto e segundo, e então exibirá o tempo nos formatos BRL (24 horas) e US (AM/PM). ✨
