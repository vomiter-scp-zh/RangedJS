A KubeJS addon for pack dev to easily create custom bows.

```
StartupEvents.registry("item", event => {
  	event.create("test_bow", "bow")
	.bow(bow => {
		bow.modifyBow(attributes => {
			attributes
			.fullChargeTicks(40) //takes 40 ticks to full charge
			.flamingArrow() //shoot flaming arrows by default
			.baseDamage(20) //base damage is set to 20 (the default is 2.0)
		})
		.onArrowHit(arrow => {
			arrow.postHurtEffect((/**@type {Internal.LivingEntity} */ livingEntity) => {
				livingEntity.potionEffects.add("wither", 1000) //add wither effect to entity hit by arrows
			})	
		})
	})
})
```