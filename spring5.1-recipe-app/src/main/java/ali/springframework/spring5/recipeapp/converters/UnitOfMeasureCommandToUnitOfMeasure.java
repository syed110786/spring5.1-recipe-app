package ali.springframework.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import ali.springframework.spring5.recipeapp.commands.UnitOfMeasureCommand;
import ali.springframework.spring5.recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source==null) {
			return null;
		}
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		return uom;
	}
}
