# Template de projet pour le TP JPA 2021 UniR

```mermaid
classDiagram
class Animal {
    <<abstract>>
    - id : Long
    # name : String
    # owner : Owner
    # age : int
}
class Owner {
    - id : Long
    - name : String
    - animals : List~Animal~
    - appointment : List~Appointment~
}
class Veterinarian {
    - id : Long
    - name : String
    - appointment : List~Appointment~
}
Animal <|-- Cat
Animal <|-- Dog
```