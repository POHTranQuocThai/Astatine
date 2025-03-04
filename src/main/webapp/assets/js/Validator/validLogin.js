const form = document.querySelector("form"),
      EmailField = form.querySelector(".email-field"),
      EmailInput = EmailField.querySelector(".email"),
      PassField = form.querySelector(".password-field"),
      PassInput = PassField.querySelector(".password");

const eyeIcons = document.querySelectorAll(".show-hide");

eyeIcons.forEach((eyeIcon) => {
  eyeIcon.addEventListener("click", () => {
    const pInput = eyeIcon.parentElement.querySelector("input");
    if (pInput.type === "password") {
      eyeIcon.classList.replace("bxs-hide", "bxs-show");
      return (pInput.type = "text");
    }
    eyeIcon.classList.replace("bxs-show", "bxs-hide");
    pInput.type = "password";
  });
});

function checkEmailLogin() {
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!EmailInput.value.match(emailPattern)) {
        return EmailField.classList.add("invalid");
    }
    EmailField.classList.remove("invalid");
}

function checkPassLogin() {
    if (PassInput.value.length < 6) {
        return PassField.classList.add("invalid");
    }
    PassField.classList.remove("invalid");
}

form.addEventListener("submit", (e) => {
    e.preventDefault();
    checkEmailLogin();
    checkPassLogin();
    EmailInput.addEventListener("keyup", checkEmailLogin);
    PassInput.addEventListener("input", checkPassLogin);
    if (
        !EmailField.classList.contains("invalid") &&
        !PassField.classList.contains("invalid")
    ) {
        form.submit();
    }
});
