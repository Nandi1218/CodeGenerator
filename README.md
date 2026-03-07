```mermaid
classDiagram
    direction BT
    class Command {
      &amp;lt;&amp;lt;Interface&amp;gt;&amp;gt;
      + execute(String) void
      String commandName
    }
    ToggleCommand &quot;1&quot; *--&gt; &quot;config 1&quot; Configuration


classDiagram
direction BT
class Command {
<<Interface>>
  + execute(String) void
   String commandName
}
class CompositeEntityValidator {
  + CompositeEntityValidator() 
  + validate(EntityModel) void
  + addValidator(EntityValidator) CompositeEntityValidator
}
class Configuration {
  + Configuration() 
  - String outputDirectory
  - boolean generateRepository
  - String targetPackage
  - boolean generateService
   String targetPackage
   boolean generateRepository
   boolean generateService
   String outputDirectory
}
class EntityModel {
  + EntityModel() 
  + EntityModel(CompositeEntityValidator, ImportProcessor, PrimaryKeyProcessor) 
  - List~FieldModel~ fields
  - String name
  - Set~String~ imports
  + validate() void
   Set~String~ imports
   String name
   String keyType
   List~FieldModel~ fields
}
class EntityValidator {
<<Interface>>
  + validate(EntityModel) void
}
class FieldModel {
  + FieldModel() 
  - String name
  - String type
  - boolean isArray
  - List~String~ modifiers
  + getIsArray() boolean
   String name
   String type
   boolean isArray
   List~String~ modifiers
   String capitalName
}
class GeneratedValueValidator {
  + GeneratedValueValidator() 
  - isNumericType(String) boolean
  + validate(EntityModel) void
}
class GeneratorService {
  + GeneratorService(Configuration) 
  ~ render(String, EntityModel) String
  + generate(List~EntityModel~) void
  - saveToFile(String, String, String) void
}
class HelpCommand {
  + HelpCommand() 
  + execute(String) void
   String commandName
}
class IllegalDSLInputException {
  + IllegalDSLInputException(String) 
}
class ImportProcessor {
  + ImportProcessor() 
  - isValidationAnnotation(String) boolean
  + processImports(EntityModel) void
  - processFieldModifiers(FieldModel, Set~String~) void
  - processFieldType(FieldModel, Set~String~) void
}
class InputController {
  + InputController(Consumer~String~) 
  + handleInput(String) void
  + registerCommand(Command) void
}
class ListCommand {
  + ListCommand(Configuration) 
  + execute(String) void
  - listCurrentSettings() String
   String commandName
}
class LoadCommand {
  + LoadCommand(Consumer~String~) 
  + execute(String) void
   String commandName
}
class Main {
  + Main() 
  + main(String[]) void
  - dslInput(String, Scanner) String
  - processAndGenerate(String, Configuration, GeneratorService) void
  - setCommands(InputController, Configuration, GeneratorService) void
}
class PrimaryKeyProcessor {
  + PrimaryKeyProcessor() 
  + processPrimaryKey(EntityModel) void
  - findPrimaryKey(EntityModel) FieldModel?
  - createDefaultPrimaryKey(EntityModel) void
  - movePrimaryKeyToFirst(EntityModel, FieldModel) void
}
class PrimaryKeyValidator {
  + PrimaryKeyValidator() 
  + validate(EntityModel) void
}
class Result {
  - Result(FieldModel, String) 
  + field() FieldModel
  + type() String
}
class SetLocationCommand {
  + SetLocationCommand(Configuration) 
  + execute(String) void
   String commandName
}
class SetPackageCommand {
  + SetPackageCommand(Configuration) 
  + execute(String) void
   String commandName
}
class SpringVisitor {
  + SpringVisitor() 
  + visitMIN(MINContext) Object
  + visitSimpleType(SimpleTypeContext) Object
  + visitProperty(PropertyContext) Object
  + visitUNIQUE(UNIQUEContext) Object
  + visitListType(ListTypeContext) Object
  + visitMIN_LENGTH(MIN_LENGTHContext) Object
  + visitMAX_LENGTH(MAX_LENGTHContext) Object
  + visitPRIMARY(PRIMARYContext) Object
  + visitREQUIRED(REQUIREDContext) Object
  + visitEMAIL(EMAILContext) Object
  + visitGENERATED(GENERATEDContext) Object
  + visitLENGTH(LENGTHContext) Object
  + visitMAX(MAXContext) Object
  + visitEntity(EntityContext) Object
  + visitOPTIONAL(OPTIONALContext) Object
  + visitModel(ModelContext) Object
  - format(TerminalNode) String
}
class ToggleCommand {
  + ToggleCommand(Configuration) 
  + execute(String) void
   String commandName
}

CompositeEntityValidator  ..>  EntityValidator 
CompositeEntityValidator "1" *--> "validators *" EntityValidator 
EntityModel "1" *--> "validator 1" CompositeEntityValidator 
EntityModel  ..>  CompositeEntityValidator : «create»
EntityModel "1" *--> "fields *" FieldModel 
EntityModel  ..>  GeneratedValueValidator : «create»
EntityModel "1" *--> "importProcessor 1" ImportProcessor 
EntityModel  ..>  ImportProcessor : «create»
EntityModel "1" *--> "primaryKeyProcessor 1" PrimaryKeyProcessor 
EntityModel  ..>  PrimaryKeyProcessor : «create»
EntityModel  ..>  PrimaryKeyValidator : «create»
GeneratedValueValidator  ..>  EntityValidator 
GeneratedValueValidator  ..>  IllegalDSLInputException : «create»
GeneratorService "1" *--> "config 1" Configuration 
HelpCommand  ..>  Command 
InputController "1" *--> "commands *" Command 
ListCommand  ..>  Command 
ListCommand "1" *--> "config 1" Configuration 
LoadCommand  ..>  Command 
Main  ..>  Configuration : «create»
Main  ..>  GeneratorService : «create»
Main  ..>  HelpCommand : «create»
Main  ..>  InputController : «create»
Main  ..>  ListCommand : «create»
Main  ..>  LoadCommand : «create»
Main  ..>  SetLocationCommand : «create»
Main  ..>  SetPackageCommand : «create»
Main  ..>  SpringVisitor : «create»
Main  ..>  ToggleCommand : «create»
PrimaryKeyProcessor  ..>  FieldModel : «create»
PrimaryKeyValidator  ..>  EntityValidator 
PrimaryKeyValidator  ..>  IllegalDSLInputException : «create»
Result "1" *--> "field 1" FieldModel 
SpringVisitor  -->  Result 
SetLocationCommand  ..>  Command 
SetLocationCommand "1" *--> "config 1" Configuration 
SetPackageCommand  ..>  Command 
SetPackageCommand "1" *--> "config 1" Configuration 
SpringVisitor  ..>  EntityModel : «create»
SpringVisitor  ..>  FieldModel : «create»
SpringVisitor  ..>  Result : «create»
ToggleCommand  ..>  Command 
ToggleCommand "1" *--> "config 1" Configuration 
```
