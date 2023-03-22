#### Реализован тест на сайт магазина http://demowebshop.tricentis.com/
![изображение](https://user-images.githubusercontent.com/118796374/227031252-04a0e2b8-c196-48e9-9216-ee1ae1fe28e3.png)

В рамках теста реализовано получение куки пользователя по API для дальнейшего использования

Тест по API отправляет отзыв о товаре методом 
```bash
POST
https://demowebshop.tricentis.com/productreviews/31
```
В теле которого передаются Заголовок и Тектс отзыва из файла *properties* директории *resources*. Например,
```bash
AddProductReview.Title=like&
AddProductReview.ReviewText=work fast&
AddProductReview.Rating=5&
add-review=Submit+review
```
С использованием Selenide производится проверка наличия отправленного отзыва в последнем элементе
