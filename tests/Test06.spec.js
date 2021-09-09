import { Selector } from 'testcafe';
import { ClientFunction } from 'testcafe';
import page from './method.po';


fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const checkBox  = Selector('a').withExactText('Controlgroup');
    await t.click(checkBox);
})

test('Dropdown length Test', async t => {

    const iframe    = Selector(".demo-frame"); 
    await t .switchToIframe(iframe);
    const dd = Selector("#car-type-button");
    await t.expect(dd.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
    const r1 = Selector('input[type=radio]').nth(0);
    await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
    const r2 = Selector('input[type=radio]').nth(1);
    await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
    const ex1 = "rgb(0, 127, 255";
    const ex2 = "rgb(246, 246, 246)";
    
    await t.click(dd)
    .pressKey("down").pressKey("down").pressKey("down").pressKey("enter")
    const optn = Selector('#car-type-button > span.ui-selectmenu-text');
    const txt  = await optn.innerText;
    await t
    .click(Selector('label').withAttribute('for', 'radio-1'));
    await t
    .expect("SUV").eql(txt)
    .expect(ex1).ok(r1.getStyleProperty('backgroundcolor'))
    .expect(ex1).notOk(r2.getStyleProperty('backgroundcolor'))
    
})