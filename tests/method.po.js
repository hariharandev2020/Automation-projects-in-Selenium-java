import { Selector } from 'testcafe';
import { ClientFunction } from 'testcafe';

class Page {
    constructor () {
        
    }
    visiblityCheck(selector) {
        return Selector(selector).exists;
    }
    buttonVisiblity(button){
        return button.exists;
    }
    browserscroll = ClientFunction(function() {
        window.scrollBy(0,500)
    });
    
}

export default new Page();