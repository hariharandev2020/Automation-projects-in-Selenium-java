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
const c5     = Selector('input[type=checkbox]').nth(0);
const c6     = Selector('input[type=checkbox]').nth(1);
const c7     = Selector('input[type=checkbox]').nth(2);
const c8    = Selector('input[type=checkbox]').nth(3);
const ex1    = "rgb(0, 127, 255";
const ex2    = "rgb(246, 246, 246)";
const lbl1   = Selector('label[for=checkbox-nested-1]');
const lbl2   = Selector('label[for=checkbox-nested-2]');
const lbl3    = Selector('label[for=checkbox-nested-3]');
const lbl4    = Selector('label[for=checkbox-nested-4]');

test("Checkbox -5-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox5     = Selector('input[type=checkbox]').nth(0);
    const checkBox6     = Selector('input[type=checkbox]').nth(1);
    const checkBox7     = Selector('input[type=checkbox]').nth(2);
    const checkBox8    = Selector('input[type=checkbox]').nth(3);
    await t.expect(checkBox5.hasAttribute('disabled')).notOk('ready for testing');        
    await t.expect(checkBox6.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(checkBox7.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(checkBox8.hasAttribute('disabled')).notOk('ready for testing')
        const actual = Selector('label[for=checkbox-nested-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Double', 'check element text');
})
test("Checkbox -6-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const actual = Selector('label[for=checkbox-nested-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Queen', 'check element text');
})
test("Checkbox -7-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const actual = Selector('label[for=checkbox-nested-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 1 Queen', 'check element text');
})
test("Checkbox -8-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const actual = Selector('label[for=checkbox-nested-4]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 1 King', 'check element text');
})
test('Nested Checkbox -1-test', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame');
        const label1   = Selector('label[for=checkbox-nested-1]');
        await t.click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .expect(label1.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -2-test', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame');
        const label2   = Selector('label[for=checkbox-nested-2]');
        await t.click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .expect(label2.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -3-test', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame');
        const label3   = Selector('label[for=checkbox-nested-3]');
        await t.click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .expect(label3.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -4-test', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame');
        const label4   = Selector('label[for=checkbox-nested-4]');
        await t.click(Selector('label').withAttribute('for', 'checkbox-nested-4'))
        .expect(label4.classNames).contains('ui-checkboxradio-checked');
})
test('Checkbox -1,2 test', async t => {
    page.browserscroll();
        await t .switchToIframe('.demo-frame');
    const checkBox5     = Selector('input[type=checkbox]').nth(0);
    const checkBox6     = Selector('input[type=checkbox]').nth(1);
    const checkBox7     = Selector('input[type=checkbox]').nth(2);
    const checkBox8    = Selector('input[type=checkbox]').nth(3);
    await t.click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .expect(ex1).ok(checkBox5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox6.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(checkBox7.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(checkBox8.getStyleProperty('backgroundcolor')); 
})
test("Checkbox -1,2,3",async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox5     = Selector('input[type=checkbox]').nth(0);
    const checkBox6     = Selector('input[type=checkbox]').nth(1);
    const checkBox7     = Selector('input[type=checkbox]').nth(2);
    const checkBox8    = Selector('input[type=checkbox]').nth(3);
    await t.click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .expect(ex1).ok(checkBox5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox7.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(checkBox8.getStyleProperty('backgroundcolor')); 
})
test('Checkbox 1,2,3,4 test', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const checkBox5     = Selector('input[type=checkbox]').nth(0);
    const checkBox6     = Selector('input[type=checkbox]').nth(1);
    const checkBox7     = Selector('input[type=checkbox]').nth(2);
    const checkBox8    = Selector('input[type=checkbox]').nth(3);
    await t.click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-4'))
        .expect(ex1).ok(checkBox5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox7.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox8.getStyleProperty('backgroundcolor'));  
})