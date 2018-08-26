# CRUD_console
Crud application console

    Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:
    
    Developer
    Skill
    Account
    
    Developer -> Set<Skill> skills + Account account
    
    В качестве хранилища данных необходимо использовать текстовые файлы:
    developers.txt, skills.txt, accounts.txt
    
    Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.
    
    Слои:
    model - POJO класы
    repository - классы, реализующие доступ к текстовым файлам
    controller - обработка запросов от пользователя
    view - все данные, необходимые для работы с консолью
    
    Например: Developer, DeveloperRepository, DeveloperController, DeveloperView и т.д.
    
    
    Для репозиторного слоя желательно использовать базовый интерфейс:
    interface GenericRepository<T,ID>
    
    interface DeveloperRepository extends GenericRepository<Developer, Long>
    
    class JavaIODeveloperRepositoryImpl implements DeveloperRepository