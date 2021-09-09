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
const iframe= Selector(".demo-frame"); 
const dd    = Selector("#car-type-button");
const dd2   = Selector("#ui-id-8");
const ddName= Selector("#car-type-button > span.ui-selectmenu-text");
const ddName2= Selector("#ui-id-8 > span.ui-selectmenu-text");
const r1    = Selector('input[type=radio]').nth(0);
const r2    = Selector('input[type=radio]').nth(1);
const r3    = Selector('input[type=radio]').nth(2);
const r4    = Selector('input[type=radio]').nth(3);
const cb    = Selector('#insurance');
const hs    = Selector('#horizontal-spinner');
const vs    = Selector('#vertical-spinner');
const ex1   = "rgb(0, 127, 255";
const ex2   = "rgb(246, 246, 246)";
const lbl1  = Selector('label[for=transmission-standard]');
const lbl1v  = Selector('label[for=transmission-standard-v]');
const lbl2  = Selector('label[for=transmission-automatic]');
const lbl2v  = Selector('label[for=transmission-automatic-v]');
const lbl3  = Selector('label[for=insurance]');
const lbl3v  = Selector('label[for=insurance-v]');
const lbl4  = Selector('.ui-controlgroup-label-contents').nth(0);
const lbl5  = Selector('.ui-controlgroup-label-contents').nth(1);
const btn   = Selector('button').withExactText('Book Now!');
const btn2  = Selector('#book');
const up    = Selector('a[role = button]').nth(0);
const up1    = Selector('a[role = button]').nth(1);
const down  = Selector('a[role = button]').nth(2);
const down2  = Selector('a[role = button]').nth(3);

test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await ddName.innerText;
    await t.expect(dd.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql('Compact car');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl1.innerText;
    await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Standard');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl2.innerText;
    await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Automatic');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl3.innerText;
    await t.expect(actual.toString()).eql(' Insurance');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl4.innerText;
    await t.expect(actual.toString()).eql('# of cars');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await btn.innerText;
    await t.expect(actual).eql('Book Now!');
})
test('Dropdown length Test', async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(dd);
    const lists =  Selector('#car-type > option');
    await t.expect(await lists.count).eql(7);
    
})
test('Dropdown Test -1', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(dd);
    const optn = Selector('#ui-id-4');
    await t.click(optn);
    await t
    .expect("SUV").eql(await ddName.innerText);
})
test('Dropdown Test -2', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl1)
    .expect(lbl1.classNames).contains('ui-checkboxradio-checked')
})
test('Dropdown Test -3', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl2)
    .expect(lbl2.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -4', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl3)
    .expect(lbl3.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -5', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(hs)
    .typeText(hs,'2')
    await t
    .click(btn)
    .expect('2').eql(await hs.value); 
})
test('Dropdown Test -6', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(hs)
    .typeText(hs,'e@#$999')
    await t
    .expect('e@#$999').eql(await hs.value,"Test case Pass"); 
})
test('Dropdown Test -7', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(hs)
    .click(up);
    await t
    .expect('1').eql(await hs.value); 
})

test('Dropdown Test -8', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(hs)
    .click(down);
    await t
    .expect('-1').eql(await hs.value,"Test case fail"); 
})
test('Dropdown Test -8-1', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(btn)
})
// .....................................
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await ddName.innerText;
    await t.expect(dd2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql('Compact car');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl1v.innerText;
    await t.expect(r3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Standard');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl2v.innerText;
    await t.expect(r4.hasAttribute('d.isabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Automatic');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl3v.innerText;
    await t.expect(actual.toString()).eql(' Insurance');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await lbl5.innerText;
    await t.expect(actual.toString()).eql('# of cars');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe(iframe)
    const actual = await btn2.innerText;
    await t.expect(actual.toString()).eql('Book Now!');
})
test('Dropdown length Test', async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(dd2);
    const lists =  Selector('#ui-id-8 > li');
    await t.expect(await lists.count).eql(7);  
})
test('Dropdown Test -1', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(dd2);
    const optn = Selector('.ui-menu-item > #ui-id-15');
    await t.click(optn)
    .expect("Van").eql(await ddName.innerText);
})
test('Dropdown Test -2', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl1v)
    .expect(lbl1v.classNames).contains('ui-checkboxradio-checked')
})
test('Dropdown Test -3', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl2v)
    .expect(lbl2v.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -4', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(lbl3v)
    .expect(lbl3v.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -5', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(vs)
    .typeText(vs,'2');
    await t
    .expect('2').eql(await hs.value); 
})
test('Dropdown Test -6', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(vs)
    .typeText(vs,'e@#$999')
    await t
    .expect('e@#$999').eql(await hs.value,"Test case Pass"); 
})
test('Dropdown Test -7', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(vs)
    .click(up1);
    await t
    .expect(1).eql(await hs.value); 
})

test('Dropdown Test -8', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(vs)
    .click(down2);
    await t
    .expect('-1').eql(await hs.value,"Test case fail"); 
})
test('Dropdown Test -8-1', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(btn2)
})
