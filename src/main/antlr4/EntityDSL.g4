grammar EntityDSL;

/*
entity [Name] {
    [pName]: [pType] [[modifier] [modifier] …]
    ...
}
*/




// Parser rules
model : entity* EOF;
entity : 'entity' ID '{' property* '}';
property : ID ':' type modifier* ';'? ;
type : ID;
modifier    : 'primary'                     #PRIMARY
            | 'generated'                   #GENERATED
            | 'required'                    #REQUIRED
            | 'unique'                      #UNIQUE
            | 'optional'                    #OPTIONAL
            | 'min' '(' INT ')'             #MIN
            | 'max' '(' INT ')'             #MAX
            | 'length' '(' INT ',' INT ')'  #LENGTH
            | 'minLength' '(' INT ')'       #MIN_LENGTH
            | 'maxLength' '(' INT ')'       #MAX_LENGTH
//          | reláció modifiererek
//          |
            ;

// Lexer rules
ID  : [a-zA-Z_][a-zA-Z0-9_]* ;
INT : [0-9]+ ;
WS  : [ \t\r\n]+ -> skip ;