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
    await t .switchToIframe(iframe)
    await t.expect(c5.hasAttribute('disabled')).notOk('ready for testing');        
    await t.expect(c6.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(c7.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(c8.hasAttribute('disabled')).notOk('ready for testing')
        await t.expect(c5.hasAttribute('disabled')).notOk('ready for testing');        
        const actual = Selector('label[for=checkbox-nested-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Double', 'check element text');
})
test("Checkbox -6-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = Selector('label[for=checkbox-nested-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Queen', 'check element text');
})
test("Checkbox -7-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = Selector('label[for=checkbox-nested-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 1 Queen', 'check element text');
})
test("Checkbox -8-active", async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = Selector('label[for=checkbox-nested-4]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 1 King', 'check element text');
})
test('Nested Checkbox -1-test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .expect(lbl1.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -2-test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .expect(lbl2.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -3-test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .expect(lbl3.classNames).contains('ui-checkboxradio-checked');
})
test('Nested Checkbox -4-test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-4'))
        .expect(lbl4.classNames).contains('ui-checkboxradio-checked');
})
test('Checkbox -1,2 test', async t => {
    page.browserscroll();
        await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c7.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(c8.getStyleProperty('backgroundcolor')); 
})
test("Checkbox -1,2,3",async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
        .click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c7.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(c8.getStyleProperty('backgroundcolor')); 
})
test('Checkbox 1,2,3,4 test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    await t
        .click(Selector('label').withAttribute('for', 'checkbox-nested-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-3'))
        .click(Selector('label').withAttribute('for', 'checkbox-nested-4'))
        .expect(ex1).ok(c5.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c6.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c7.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(c8.getStyleProperty('backgroundcolor'));  
})