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
    const iframe    = Selector(".demo-frame"); 
    const r1 = Selector('input[type=radio]').nth(0);
    const r2 = Selector('input[type=radio]').nth(1);
    const r3 = Selector('input[type=radio]').nth(2);
    const ex1 = "rgb(0, 127, 255";
    const ex2 = "rgb(246, 246, 246)";

test('Radio 1', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        const actual = Selector('label[for=radio-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' New York', 'check element text', { timeout: 500 });
})
test('Radio 2', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        const actual = Selector('label[for=radio-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' Paris', 'check element text', { timeout: 500 });   
})
test('Radio 3', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        const actual = Selector('label[for=radio-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' London', 'check element text', { timeout: 500 });

})  
test('Radio -4 test', async t => {
    page.browserscroll(); 
        await t .switchToIframe(iframe)
        await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const lbl    = Selector('label[for=radio-1]');
        await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t.expect(r3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t
        .click(Selector('label').withAttribute('for', 'radio-1'))
        .expect(ex1).ok(r1.getStyleProperty('backgroundcolor'))
        .expect(lbl.classNames).contains('ui-checkboxradio-checked')
        .expect(ex2).ok(r2.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(r3.getStyleProperty('backgroundcolor'))
        
})
test('Radio -5 test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        const lbl    = Selector('label[for=radio-2]');
        await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        await t.expect(r3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
        .click(Selector('label').withAttribute('for', 'radio-2'));
        await t
        .expect(ex2).ok(r1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(r2.getStyleProperty('backgroundcolor'))
        .expect(lbl.classNames).contains('ui-checkboxradio-checked')
        .expect(ex2).ok(r3.getStyleProperty('backgroundcolor'));          
})
test('Radio -6 test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        const lbl    = Selector('label[for=radio-3]');
        await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        await t.expect(r3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const ex1 = "rgb(0, 127, 255";
        const ex2 = "rgb(246, 246, 246)";
        await t
        .click(Selector('label').withAttribute('for', 'radio-3'));
        await t
        .expect(ex2).ok(r1.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(r2.getStyleProperty('backgroundcolor'))
        .expect(lbl.classNames).contains('ui-checkboxradio-checked')
        .expect(ex1).ok(r3.getStyleProperty('backgroundcolor'));          
})

