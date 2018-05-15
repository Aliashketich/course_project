
$(document).ready(function ($) {
    $('form#autorizForm').on('click', function (e) {

// Запрещаем стандартное поведение для кнопки submit
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');
        var $form_modal = $('.cd-user-modal');
        var $form_sign_in = $form_modal.find('#sign_in');
        var $numberCorrectField = 0;

        var password = $('input#password_in').val();
        var login = $('input#login_in').val();

        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#password_in').css('border-color', 'green');
            $('input#password_in').removeClass('has-error').next('span#password-span').removeClass('is-visible');
            $numberCorrectField++;
        }
        else {
            if (password.length != 0) {
                $('input#password_in').css('border-color', 'red');
                $('input#password_in').addClass('has-error').next('span#password-span').addClass('is-visible');
            }
        }
        if (login.length > 3 && login != '' && loginReg.test(login)) {
            $('input#login_in').css('border-color', 'green');
            $('input#login_in').removeClass('has-error').next('span#login-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#login_in').css('border-color', 'red');
                $('input#login_in').addClass('has-error').next('span#login-span').addClass('is-visible');
            }
        }
        if ($numberCorrectField != 2) {
            e.preventDefault();
        }
    });
});