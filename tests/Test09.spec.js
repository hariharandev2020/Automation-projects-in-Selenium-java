import { Selector,ClientFunction } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const menuBtn  = Selector('a').withExactText('Menu');
    await t.click(menuBtn);
});
const iframe = Selector('iframe');
const menu   = Selector('#menu');
const li1    = Selector('#menu>li').nth(0);
const li2    = Selector('li>#ui-id-2');
const li3    = Selector('li>#ui-id-3');
const li4    = Selector('li>#ui-id-4');
const li5    = Selector('#ui-id-5');
const li6    = Selector('#ui-id-6');
const li7    = Selector('#ui-id-7');
const li8    = Selector('#ui-id-8');
const li9    = Selector('#ui-id-9');
const li10   = Selector('#ui-id-10');
const li11   = Selector('#ui-id-11');
const li12   = Selector('#ui-id-12');
const li13   = Selector('#ui-id-13');
const li14   = Selector('#ui-id-14');
const li15   = Selector('#ui-id-15');
const li16   = Selector('#ui-id-16');
const li17   = Selector('#ui-id-17');
const li18   = Selector('#menu>li').nth(6);
const ul1   = Selector('#menu > li:nth-child(4) > ul');
const ul2   = Selector('#menu > li:nth-child(6) > ul');
const ul3   = Selector('#menu > li:nth-child(6) > ul > li:nth-child(1) > ul');
const ul4   = Selector('#menu > li:nth-child(6) > ul > li:nth-child(2) > ul');

test('Menu item count check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(Selector('#menu>li').count).eql(7);
})

test('Menu item text check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(await li1.getAttribute('class')).contains('ui-state-disabled');
    await t.expect(await li2.innerText).eql('Books');
    await t.expect(await li3.innerText).eql('Clothing');
    await t.expect(await li4.innerText).eql('Electronics');
    await t.expect(await li8.innerText).eql('Movies');
    await t.expect(await li9.innerText).eql('Music');
    await t.expect(await li18.getAttribute('class')).contains('ui-state-disabled');
})
test('Menu item text color check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.hover(li2);
    await t.expect(await li2.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.hover(li3);
    await t.expect(await li3.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.hover(li8);
    await t.expect(await li8.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    
})
test('Menu item-4 check', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
    await t.click(li4);
    await t.expect(await ul1.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(4) > ul>li').count).eql(3);
    await t.expect(await li5.innerText).eql('Home Entertainment');
    await t.expect(await Selector('#menu > li:nth-child(4) > ul > li.ui-state-disabled.ui-menu-item').getAttribute('class')).contains('ui-state-disabled');
    await t.expect(await (await li6.innerText)).eql('Car Hifi');
    await t.expect(await (await li7.innerText)).eql('Utilities');
    await t.hover(li6);
    await t.expect(await li6.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.click(li6);
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
    await t.click(li4);
    await t.hover(li7);
    await t.expect(await ul1.getStyleProperty('display')).eql('block');
    await t.expect(await li7.getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.click(li7);
    await t.expect(await ul1.getStyleProperty('display')).eql('none');
})
test('Menu item 8-1 test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(await ul2.getStyleProperty('display')).eql('none');
    await t.hover(li9);
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul>li').count).eql(3);
    await t.expect(await ul3.getStyleProperty('display')).eql('none');
    await t.click(li9);
    await t.click(li10);
    await t.expect(await Selector(li10).getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul > li:nth-child(1) > ul>li').count).eql(2);
    await t.expect(await li11.innerText).eql('Alternative');
    await t.expect(await li12.innerText).eql('Classic');
})
test('Menu item 8-2 test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(await ul2.getStyleProperty('display')).eql('none');
    await t.hover(li9);
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul>li').count).eql(3);
    await t.expect(await ul4.getStyleProperty('display')).eql('none');
    await t.click(li9);
    await t.click(li13);
    await t.expect(await Selector(li13).getStyleProperty('background-color')).eql('rgb(0, 127, 255)');
    await t.expect(await ul2.getStyleProperty('display')).eql('block');
    await t.expect(await Selector('#menu > li:nth-child(6) > ul > li:nth-child(2) > ul>li').count).eql(3);
    await t.expect(await li14.innerText).eql('Freejazz');
    await t.expect(await li15.innerText).eql('Big Band');
    await t.expect(await li16.innerText).eql('Modern');
    await t.click(li16);
    await t.expect(await ul4.getStyleProperty('display')).eql('none');
    await t.expect(await ul3.getStyleProperty('display')).eql('none');

})
