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
const iframe = Selector(".demo-frame"); 
const c1     = Selector('input[type=checkbox]').nth(0);
const c2     = Selector('input[type=checkbox]').nth(1);
const c3     = Selector('input[type=checkbox]').nth(2);
const c4     = Selector('input[type=checkbox]').nth(3);
const ex1    = "rgb(0, 127, 255";
const ex2    = "rgb(246, 246, 246)";
const lbl1   = Selector('label[for=checkbox-1]');
const lbl2   = Selector('label[for=checkbox-2]');
const lbl3    = Selector('label[for=checkbox-3]');
const lbl4    = Selector('label[for=checkbox-4]');

test.skip("Checkbox -1-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    await t.expect(c1.hasAttribute('disabled')).notOk('ready for testing');        
    await t.expect(c2.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(c3.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(c4.hasAttribute('disabled')).notOk('ready for testing')
        await t.expect(c1.hasAttribute('disabled')).notOk('ready for testing');        
        const actual = Selector('label[for=checkbox-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Star', 'check element text');
})
test.skip("Checkbox -2-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        const actual = Selector('label[for=checkbox-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 3 Star', 'check element text');
})
test.skip("Checkbox -3-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .expect(c3.hasAttribute('disabled')).notOk('ready for testing');
        const actual = Selector('label[for=checkbox-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 4 Star', 'check element text', { timeout: 500 });
})
test.skip("Checkbox -4-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .expect(c4.hasAttribute('disabled')).notOk('ready for testing')
        const actual = Selector('label[for=checkbox-4]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 5 Star', 'check element text', { timeout: 500 });
})
test("Checkbox-1", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-1'));   
        await t.expect(lbl1.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-2", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-2'));   
        await t.expect(lbl2.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-3", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-3'));   
        await t.expect(lbl3.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-4", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-4'));   
        await t.expect(lbl4.classNames).contains('ui-checkboxradio-checked')
})
test.skip('Checkbox -1,2 test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c3.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(c4.getStyleProperty('backgroundcolor')); 
})
test.skip("Checkbox -1,2,3",async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-3'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c3.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c4.getStyleProperty('backgroundcolor')); 
})
test.skip('Checkbox 1,2,3,4 test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    await t
        .click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-3'))
        .click(Selector('label').withAttribute('for', 'checkbox-4'))
        .expect(ex1).ok(c1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c3.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c4.getStyleProperty('backgroundcolor'));  
})