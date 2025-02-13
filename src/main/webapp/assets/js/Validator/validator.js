
function Validator(option) {

    function getParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    let selectorRules = {};
    //Hàm thực hiện validate
    function validate(inputElement, rule) {
        let errorMessage;
        let errorElement = getParent(inputElement, option.formGroupSelector).querySelector(option.errorSelector);

        //Lấy ra các rules của selector
        let rules = selectorRules[rule.selector];
        //lặp qua từng rule & kiểm tra
        //Nếu lỗi thì dừng
        for (let i = 0; i < rules.length; ++i) {
            switch (inputElement.type) {
                case 'radio':
                case 'checkbox':
                    errorMessage = rules[i](
                            formElement.querySelector(rule.selector + ':checked')
                            );
                    break;
                default:
                    errorMessage = rules[i](inputElement.value);
            }
            if (errorMessage)
                break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            getParent(inputElement, option.formGroupSelector).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            getParent(inputElement, option.formGroupSelector).classList.remove('invalid');
        }

        return !errorMessage;
    }

    // lấy element của form cần validate
    let formElement = document.querySelector(option.form);
    if (formElement) {
        //khi nhấn submit   
        formElement.onsubmit = (e) => {
            let isFormValid = true;

            // Lặp qua từng rules và validate
            option.rules.forEach(function (rule) {
                let inputElement = formElement.querySelector(rule.selector);
                let isValid = validate(inputElement, rule);
                if (!isValid) {
                    isFormValid = false;
                }
            });

            // Nếu form không hợp lệ, ngăn chặn submit
            if (!isFormValid) {
                e.preventDefault();
            } else {
                // Nếu hợp lệ, xử lý submit
                if (typeof option.onsubmit === 'function') {
                    let enableInputs = formElement.querySelectorAll('[name]:not([disabled])');
                    let formValues = Array.from(enableInputs).reduce((values, input) => {
                        switch (input.type) {
                            case 'radio':
                            case 'checkbox':
                                values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                                break;
                            default:
                                values[input.name] = input.value;
                        }
                        return values;
                    }, {});
                    option.onsubmit(formValues);
                }
            }
        }


        //Lặp qua mỗi rule và xử lý (lắng nghe sự kiện blur, input,...)
        option.rules.forEach(function (rule) {

            //Lưu lại các rules cho mỗi input
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }

            let inputElements = formElement.querySelectorAll(rule.selector);

            Array.from(inputElements).forEach((inputElement) => {
                //Xử lý trường hợp blur khỏi input
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                }
                inputElement.oninput = () => {
                    let errorElement = getParent(inputElement, option.formGroupSelector).querySelector(option.errorSelector);
                    errorElement.innerText = '';
                    getParent(inputElement, option.formGroupSelector).classList.remove('invalid');
                }
            });
        });
    }
}

//Định nghĩa rules
// Nguyên tắc của các rules:
//1. Khi có lỗi => Trả ra meessage lỗi
//2. Khi hợp lệ => ko trả gì (undefined)
Validator.isRequired = function (selector, meessage) {
    return {
        selector: selector,
        test: function (value) {
            return value ? undefined : meessage || "vui lòng nhập trường này";
        }
    }
}

Validator.isEmail = function (selector, meessage) {
    return {
        selector: selector,
        test: function (value) {
            let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : meessage || 'Trường này phải là email';
        }
    }
}

Validator.minLength = function (selector, min, meessage) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined : meessage || `Vui lòng nhập tối thiểu ${min} kí tự`;
        }
    }
}
Validator.isConfirmed = function (selector, getConfirmValue, message) {
    return {
        selector: selector,
        test: function (value) {
            return value === getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác';
        }
    }
}