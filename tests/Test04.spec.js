import { Selector } from 'testcafe';
import page from './method.po';


fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const checkBox  = Selector('a').withExactText('Checkboxradio');
    await t.click(checkBox);
})

test('Checkbox -1 test', async t => {
    page.browserscroll();
    const iframe    = Selector(".demo-frame"); 
        await t .switchToIframe(iframe)
        const c1 = Selector('input[type=checkbox]').nth(0);
        await t.expect(c1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c2 = Selector('input[type=checkbox]').nth(1);
        await t.expect(c2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c3 = Selector('input[type=checkbox]').nth(2);
        await t.expect(c3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c4 = Selector('input[type=checkbox]').nth(3);
        await t.expect(c4.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const ex1 = "rgb(0, 127, 255";
        const ex2 = "rgb(246, 246, 246)";
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-1'));
        await t
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c3.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c4.getStyleProperty('backgroundcolor')); 
        await t   
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c3.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(c4.getStyleProperty('backgroundcolor')); 
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-3'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c3.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c4.getStyleProperty('backgroundcolor')); 
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-4'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c3.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c4.getStyleProperty('backgroundcolor'));  
})