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

test('Nested Checkbox test', async t => {
    page.browserscroll();
    const iframe    = Selector(".demo-frame"); 
        await t .switchToIframe(iframe);
        page.browserscroll();
        const c5 = Selector('input[name=checkbox-nested-1]');
        await t.expect(c5.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c6 = Selector('input[name=checkbox-nested-2]');
        await t.expect(c6.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c7 = Selector('input[name=checkbox-nested-3]');
        await t.expect(c7.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const c8 = Selector('input[name=checkbox-nested-4]');
        await t.expect(c8.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const ex1 = "rgb(0, 127, 255";
        const ex2 = "rgb(246, 246, 246)";
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-nested-1'));
        await t
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c7.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c8.getStyleProperty('backgroundcolor')); 
        await t   
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c7.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(c8.getStyleProperty('backgroundcolor')); 
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c7.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c8.getStyleProperty('backgroundcolor')); 
        await t
        .click(Selector('label').withAttribute('for', 'checkbox-nested-4'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c7.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c8.getStyleProperty('backgroundcolor'));  
})