class Validation{

    addComma(num){
        const regexp = /\B(?=(\d{3})+(?!\d))/g;
        return num.toString().replace(regexp, ',');
    }

    passwordNum(num){
        const regexp = /^[0-9]{4}$/;
        return regexp.test(num);
    }

}

export default new Validation();