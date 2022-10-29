# MusicService
## Описание проекта
### Окно авторизации
Пользователь попадает на окно авторизации где может либо авторизироваться, либо зарегистрироваться.

После регистрации пользователю приходит сообщение либо об успешной регистрации, либо об ошибке(вместе с ее описанием).

После регистрации пользователь может авторизироваться и перейти непосредственно к ресурсу.

### Основное окно ресурса
Если авторизовался пользователь с ролью "User" он может: прослушивать музыку, искать песни по названию, искать песни и альбомы исполнителя, добавлять песни в свой плейлист, купить или улучшить свою подписку. Без подписки прослушивание музыки запрещено.

У пользователя с ролью "User" из доступных вкладок есть "Муызка" где он может искать музыку и добавлять себе в плейлист, вкладка "Плейлист" где пользователь может слушать музыку из своего плейлиста и удалить треки из него, "вкладка "Подписка" где пользователь может посмотреть до какого числа у него еще есть подписка и если же подписки нет, то может купить ее.

Музыку которую может слушать пользователь обязательно должна быть проверена Админом на цензуру.

У пользователя с ролью "Artist" добавляется дополнительные вкладки "Добавить песню" и "Организация".Во вкладке "Добавить песню" он отправляет песню и дожидается пока админ ее проверит, после чего, сможет выгрузить ее на площадку.Во вкладке "Организация" по дефолту он не состоит в организации, но может выбрать существующие организации и всупить в нее.

У пользователя с ролью "Admin" добавляется дополнительные вкладки "Проверить песни","Создать юзера" и "Создать Организацию".В "Проверить песню" админ проверяет песни артистов на цензуру.Во вкладке "Создать юзера" админ может создать юзеров со всеми возможными ролями: "Admin","Artist","User". Во вклдаке "Создать организацию" админ создает организацию в которую может вступить пользователь с ролью "Artist".

## Запросы к серверу
### Регистрация пользователя
requestType : POST   
url : http://localhost:8080/api/admin/addOrganisation  
request :  
{  
    "name" : "Name",  
    "surname" : "Surname",  
    "login" : "Login",  
    "password" : "password",  
    "role" : "admin",  
    "countryId" : "Россия"  
}  
### Авторизация пользователя
requestType : POST   
url : http://localhost:8080/api/auth/signin  
request :  
{  
    "login" : "Login",  
    "password" : "password"  
}  
### Добавление подписки пользователю
requestType : POST   
url : http://localhost:8080/api/user/addSub  
request :  
{  
    "login" : "nik23",  
    "sub" : "Студенческая"  
}  
### Добавление артиста в базу
requestType : POST    
url : http://localhost:8080/api/artist/addArtist  
request :  
{  
    "description" : "Описание артиста!",  
    "login" : "login",  
    "name" : "nickname"  
}
### Получить все организации(Получить список организация может Artist и Admin)
requestType : GET  
url : http://localhost:8080/api/artist/getOrganisation  
header : Authorization Bearer + token  
### Добавить организацию(Создать организацию может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/addOrganisation  
header : Authorization Bearer + token  
request :  
{  
    "description" : "описание организации",  
    "name" : "orgName",  
    "countryName" : "Россия"  
}  
### Добавить альбом(Создать альбом может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addAlbum  
header : Authorization Bearer + token  
request :  
{  
    "userId" : Id,  
    "name" : "name",  
    "description" : "description"  
}  
### Вступить в организацию(Вступить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/setOragnisationToArtist  
header : Authorization Bearer + token  
request :  
{  
    "orgId" : orgId,  
    "userId" : userId     
}  
### Выйти из организации(Выйти может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/quitFromOrganisation  
header : Authorization Bearer + token  
request :  
{   
    "userId" : userId     
}  
### Добавить песню(Добавить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addSong  
header : Authorization Bearer + token  
request :  
{   
    "userId" : userId,  
    "name" : "name",  
    "duration" : duration,  
    "albumName" : "albumName",  
    "genre" : "genre",  
    "link" : "link"  
}  
### Получить песни на провекру(Получить может только Admin)
requestType : GET  
url : http://localhost:8080/api/admin/getSongsForAdmin  
header : Authorization Bearer + token  
### Проверить песню(Проверить может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/checkSong  
header : Authorization Bearer + token  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### Добавить песню в плэйлист
requestType : POST  
url : http://localhost:8080/api/user/addSongToPlaylist  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### Получить плэйлист
requestType : POST  
url : http://localhost:8080/api/user/getPlayList  
request :  
{  
    "userId" : userId  
}  
### Поиск песен
requestType : POST  
url : http://localhost:8080/api/user/findSongs  
request :  
{  
    "name" : "name"  
}  
