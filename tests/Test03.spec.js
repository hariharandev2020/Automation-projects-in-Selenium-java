import { Selector } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const radioBtn  = Selector('a').withExactText('Checkboxradio');
    await t.click(radioBtn);
})
test('Radio 1', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame')
        const actual = Selector('label[for=radio-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' New York', 'check element text', { timeout: 500 });
})
test('Radio 2', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame')
        const actual = Selector('label[for=radio-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' Paris', 'check element text', { timeout: 500 });   
})
test('Radio 3', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame')
        const actual = Selector('label[for=radio-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' London', 'check element text', { timeout: 500 });

})  
test('Radio -4 test', async t => {
    
    page.browserscroll(); 
        await t .switchToIframe('.demo-frame')
        const radio1 = Selector('input[type=radio]').nth(0);
        const radio2 = Selector('input[type=radio]').nth(1);
        const radio3 = Selector('input[type=radio]').nth(2);
        const expect1 = "rgb(0, 127, 255";
        const expect2 = "rgb(246, 246, 246)";
        const label    = Selector('label[for=radio-1]');
            await t.expect(radio1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
            await t.expect(radio2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
            await t.expect(radio3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
            await t
            .click(Selector('label').withAttribute('for', 'radio-1'))
            .expect(expect1).ok(radio1.getStyleProperty('backgroundcolor'))
            .expect(expect2).ok(radio2.getStyleProperty('backgroundcolor'))
            .expect(expect2).ok(radio3.getStyleProperty('backgroundcolor'))     
})
test('Radio -5 test', async t => {
    page.browserscroll();
    const radio1 = Selector('input[type=radio]').nth(0);
    const radio2 = Selector('input[type=radio]').nth(1);
    const radio3 = Selector('input[type=radio]').nth(2);
    const expect1 = "rgb(0, 127, 255";
    const expect2 = "rgb(246, 246, 246)";
    const label    = Selector('label[for=radio-1]');
        await t .switchToIframe('.demo-frame')
        await t.expect(radio1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        await t.expect(radio2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        await t.expect(radio3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        .click(Selector('label').withAttribute('for', 'radio-2'))
        .expect(expect2).ok(radio1.getStyleProperty('backgroundcolor'))
        .expect(expect1).ok(radio2.getStyleProperty('backgroundcolor'))
        .expect(expect2).ok(radio3.getStyleProperty('backgroundcolor'));          
})
test('Radio -6 test', async t => {
    page.browserscroll();
    const radio1 = Selector('input[type=radio]').nth(0);
    const radio2 = Selector('input[type=radio]').nth(1);
    const radio3 = Selector('input[type=radio]').nth(2);
    const expect1 = "rgb(0, 127, 255";
    const expect2 = "rgb(246, 246, 246)";
    const label    = Selector('label[for=radio-1]');
    const label1    = Selector('label[for=radio-2]');
    const labe2    = Selector('label[for=radio-3]');
        await t .switchToIframe('.demo-frame')
        await t.expect(radio1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t.expect(radio2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t.expect(radio3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t
        .click(Selector('label').withAttribute('for', 'radio-3'));
        await t
        .expect(expect2).ok(radio1.getStyleProperty('backgroundcolor'))
        .expect(expect2).ok(radio2.getStyleProperty('backgroundcolor'))
        .expect(expect1).ok(radio3.getStyleProperty('backgroundcolor'));          
})

