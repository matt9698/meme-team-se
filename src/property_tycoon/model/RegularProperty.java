package property_tycoon.model;

/**
 *
 * @author mm933
 */
public class RegularProperty extends PropertyImpl
{
    @Override
    public int downgrade()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRentCost(PropertyLevel level)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRentCost(PropertyLevel level, int diceValue)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PropertyType getType()
    {
        return PropertyType.REGULAR;
    }

    @Override
    public boolean isImprovable()
    {
        return true;
    }

    @Override
    public int upgrade()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
